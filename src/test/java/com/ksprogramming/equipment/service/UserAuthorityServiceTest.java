package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.UserAuthorityData;
import com.ksprogramming.equipment.data.UserData;
import com.ksprogramming.equipment.repository.UserAuthorityRepository;
import com.ksprogramming.equipment.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-integrationtest.properties")
class UserAuthorityServiceTest {
    private final String ROLE = "ADMIN";
    private LocalDateTime localDateTime = LocalDateTime.now();
    private UserData userData = new UserData(1L,"Marian", "qwerty", false, "PL", new ArrayList<>(), localDateTime);
    private UserAuthorityData userAuthority = new UserAuthorityData(userData, ROLE);
    @Autowired
    private UserAuthorityServiceInterface userAuthorityService;
    @Autowired
    private UserAuthorityRepository userAuthorityRepository;
    @Autowired
    private UserServiceInterface equipmentUserService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userAuthorityRepository.deleteAll();
        userRepository.deleteAll();
    }
    @Test
    void registerUser() {
        // WHEN
        userAuthorityRepository.deleteAll();
        UserData actualUser = equipmentUserService.registerUser(userData);
        UserAuthorityData saveUserAuthority = userAuthorityService.save(new UserAuthorityData(actualUser, ROLE));
        UserAuthorityData actualUserAuthority = userAuthorityService.get(saveUserAuthority.getId());
        List<UserAuthorityData> results = userAuthorityService.findAll();
        // EXPECTED
        assertEquals(ROLE, actualUserAuthority.getAuthority());
        assertEquals(userData.getId(), actualUserAuthority.getUserData().getId());
        assertFalse(actualUserAuthority.getUserData().getEmailConfirmed());
        assertEquals("PL", actualUserAuthority.getUserData().getLanguage());
        assertEquals("Marian", actualUserAuthority.getUserData().getLogin());
        assertEquals("qwerty", actualUserAuthority.getUserData().getPasswordHash());
        assertEquals(localDateTime.truncatedTo(ChronoUnit.SECONDS), actualUserAuthority.getUserData().getRegistrationDate().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(1, results.size());

    }

    @Test
    void update() {
        // GIVEN
        LocalDateTime localDateTime = LocalDateTime.now();
        UserData equipmentUser = equipmentUserService.registerUser(
                new UserData("adam", "qwerty", false, "PL", new ArrayList<>(), localDateTime));
        UserAuthorityData userAuthority = new UserAuthorityData(equipmentUser, "ADMIN");
        UserAuthorityData saveUserAuthority = userAuthorityService.save(userAuthority);
        // WHEN
        UserAuthorityData previousResult = userAuthorityService.get(saveUserAuthority.getId());
        previousResult.setAuthority("USER");
        userAuthorityService.update(previousResult);
        UserAuthorityData actualResult = userAuthorityService.get(saveUserAuthority.getId());
        List<UserAuthorityData> results = userAuthorityService.findAll();
        // EXPECTED
        assertEquals(saveUserAuthority.getId(), actualResult.getId());
        assertEquals(previousResult.getAuthority(), actualResult.getAuthority());
        assertNotEquals(userAuthority.getAuthority(), actualResult.getAuthority());
        assertEquals(1, results.size());
        assertEquals(equipmentUser.getId(), actualResult.getUserData().getId());
        assertEquals("adam", actualResult.getUserData().getLogin());
        assertEquals("qwerty", actualResult.getUserData().getPasswordHash());
        assertFalse(actualResult.getUserData().getEmailConfirmed());
        assertEquals("PL", actualResult.getUserData().getLanguage());
        assertEquals(localDateTime.truncatedTo(ChronoUnit.SECONDS), actualResult.getUserData().getRegistrationDate().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    void findAll() {
        // GIVEN
        LocalDateTime localDateTime = LocalDateTime.now();
        UserData equipmentUserData = equipmentUserService.registerUser(
                new UserData("adam", "qwerty", false, "PL", new ArrayList<>(), localDateTime));
        List<UserAuthorityData> userAuthorityDataList = new ArrayList<>();
        userAuthorityDataList.add(new UserAuthorityData(equipmentUserData, "ADMIN"));
        userAuthorityDataList.add(new UserAuthorityData(equipmentUserData, "USER"));
        userAuthorityDataList.add(new UserAuthorityData(equipmentUserData, "STUDENT"));
        Map<Long, UserAuthorityData> expectedUserAuthorities = new HashMap<>();
        userAuthorityDataList.stream().forEach(authority -> {
            UserAuthorityData userAuthorityData = userAuthorityService.save(authority);
            expectedUserAuthorities.put(userAuthorityData.getId(), userAuthorityData);
        });
        // WHEN
        List<UserAuthorityData> results = userAuthorityService.findAll();
        Map<Long, UserAuthorityData> actualUserAuthorities = new HashMap<>();
        results.stream().forEach(authority -> {
            actualUserAuthorities.put(authority.getId(), authority);
        });
        // EXPECTED
        for (Map.Entry<Long, UserAuthorityData> effect : expectedUserAuthorities.entrySet()) {
            UserAuthorityData actual = actualUserAuthorities.get(effect.getKey());
            UserAuthorityData expected = effect.getValue();
            assertEquals(effect.getKey(), actual.getId());
            assertEquals(expected.getAuthority(), actual.getAuthority());
            assertEquals(expected.getUserData().getId(), actual.getUserData().getId());
            assertEquals("adam", actual.getUserData().getLogin());
            assertEquals("qwerty", actual.getUserData().getPasswordHash());
            assertFalse(actual.getUserData().getEmailConfirmed());
            assertEquals(localDateTime.truncatedTo(ChronoUnit.SECONDS), actual.getUserData().getRegistrationDate().truncatedTo(ChronoUnit.SECONDS));
        }
        assertEquals(3, actualUserAuthorities.size());
    }

    @Test
    void findById() {
        // GIVEN
        LocalDateTime localDateTime = LocalDateTime.now();
        UserData equipmentUserFirst = equipmentUserService.registerUser(
                new UserData("adam", "qwerty", false, "PL", new ArrayList<>(), localDateTime));
        UserData equipmentUserSecond = equipmentUserService.registerUser(new UserData("marcin", "aaaa", true, "PL", new ArrayList<>(), localDateTime));
        List<UserAuthorityData> userAuthorities = new ArrayList<>();
        userAuthorities.add(new UserAuthorityData(equipmentUserFirst, "ADMIN"));
        userAuthorities.add(new UserAuthorityData(equipmentUserSecond, "USER"));
        Map<Long, UserAuthorityData> expectedUserAuthorities = new HashMap<>();
        userAuthorities.stream().forEach(user -> {
            UserAuthorityData userAuthority = userAuthorityService.save(user);//            assertEquals(expected.getEquipmentUserData().getId(), actual.getEquipmentUserData().getId());
            expectedUserAuthorities.put(userAuthority.getId(), userAuthority);

        });
        // WHEN
        List<UserAuthorityData> results = userAuthorityService.findById(equipmentUserFirst.getId());
        Map<Long, UserAuthorityData> actualUserAuthorities = new HashMap<>();
        results.stream().forEach(authority -> {
            actualUserAuthorities.put(authority.getId(), authority);
        });
        // EXPECTED
        for (Map.Entry<Long, UserAuthorityData> effect : expectedUserAuthorities.entrySet()) {
            UserAuthorityData actual = actualUserAuthorities.get(effect.getKey());
            UserAuthorityData expected = effect.getValue();
            if (expected.getId() == equipmentUserFirst.getId()) {
                assertEquals(expected.getId(), actual.getId());
                assertEquals(expected.getUserData().getId(), actual.getUserData().getId());
                assertEquals("adam", actual.getUserData().getLogin());
                assertEquals("qwerty", actual.getUserData().getPasswordHash());
                assertFalse(actual.getUserData().getEmailConfirmed());
                assertEquals("PL", actual.getUserData().getLanguage());
                assertEquals(expected.getUserData().getRegistrationDate().truncatedTo(ChronoUnit.SECONDS),
                        actual.getUserData().getRegistrationDate().truncatedTo(ChronoUnit.SECONDS));
            }
        }
        assertEquals(1, results.size());
    }

    @Test
    void delete() {
        // GIVEN
        LocalDateTime localDateTime = LocalDateTime.now();
        UserData equipmentUserDataFirst = equipmentUserService.registerUser(
                new UserData("adam", "qwerty", false, "PL", new ArrayList<>(), localDateTime));
        UserData equipmentUserDataSecond = equipmentUserService.registerUser(
                new UserData("piotr", "aass", false, "PL", new ArrayList<>(), localDateTime));
        UserAuthorityData expectedUserAuthorityAdam = new UserAuthorityData(equipmentUserDataFirst, "ADMIN");
        UserAuthorityData saveUserAuthorityAdam = userAuthorityService.save(expectedUserAuthorityAdam);
        UserAuthorityData expectedUserAuthorityPiotr = new UserAuthorityData(equipmentUserDataSecond, "ADMIN");
        UserAuthorityData saveUserAuthorityPiotr = userAuthorityService.save(expectedUserAuthorityPiotr);
        // WHEN
        userAuthorityService.delete(saveUserAuthorityAdam.getUserData().getId());
        List<UserAuthorityData> results = userAuthorityService.findAll();
        //
        List<Long> actualUserAuthorityId = results.stream().map(UserAuthorityData::getId).collect(Collectors.toList());
        actualUserAuthorityId.stream().forEach(actual -> {
            assertNotEquals(saveUserAuthorityAdam.getId(), actual);
            assertEquals(saveUserAuthorityPiotr.getId(), actual);
        });
        assertEquals(1, actualUserAuthorityId.size());

    }

    @Test
    void get() {
        // GIVEN
        LocalDateTime localDateTime = LocalDateTime.now();
        UserData equipmentUserData = equipmentUserService.registerUser(
                new UserData("adam", "qwerty", false, "PL", new ArrayList<>(), localDateTime));
        UserAuthorityData expectedUserAuthority = new UserAuthorityData(equipmentUserData, "ADMIN");
        UserAuthorityData saveUserAuthority = userAuthorityService.save(expectedUserAuthority);
        // WHEN
        UserAuthorityData result = userAuthorityService.get(saveUserAuthority.getId());
        // EXPECTED
        assertEquals(expectedUserAuthority.getAuthority(), result.getAuthority());

    }
}