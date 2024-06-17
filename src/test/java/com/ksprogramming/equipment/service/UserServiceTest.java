package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.UserData;
import com.ksprogramming.equipment.enumes.Language;
import com.ksprogramming.equipment.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-integrationtest.properties")
public class UserServiceTest {
    @Autowired
    private UserServiceInterface userService;
    @Autowired
    private UserRepository userRepository;
    @Before
    public void setUp(){
        userRepository.deleteAll();
    }
    @Test
    public void createUserTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        UserData userData = userService.registerUser(new UserData("Marcin", "qwerty", false, Language.POLISH.getCode(), new ArrayList<>(), localDateTime));
        UserData getUser = userService.getUserById(userData.getId());
        assertEquals("Marcin", getUser.getLogin());
        assertEquals("qwerty", getUser.getPasswordHash());
        assertEquals("pl", getUser.getLanguage());
    }
}