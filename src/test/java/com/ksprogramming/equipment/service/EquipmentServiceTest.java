package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.*;
import com.ksprogramming.equipment.repository.EquipmentRepository;
import org.junit.Before;
import org.junit.Test;
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

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-integrationtest.properties")
public class EquipmentServiceTest {
    @Autowired
    private EquipmentServiceInterface equipmentService;
    @Autowired
    private UserServiceInterface userService;
    @Autowired
    private AttributeServiceInterface attributeService;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Before
    public void setUp() {
        equipmentRepository.deleteAll();
    }
    @Test
    public void create(){
        // GIVEN
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        UserData user = userService.registerUser(new UserData("Jan", "qwerty", false, "PL", new ArrayList<>(), localDateTime));
        EquipmentData equipment = new EquipmentData(user,"Car", localDateTime);
        AttributeData attribute = attributeService.create(new AttributeData("Mark", "String", "Equipment", localDateTime));
        List<ValueData> values = new ArrayList<>();
        values.add(new ValueData(attribute.getId(), "BMW", "Mark"));
        EquipmentData expectedEquipment = equipmentService.create(equipment, values);
        // WHEN
        List<EquipmentData> results = equipmentService.findAll();
        // EXPECTED
        assertEquals(1, results.size());
        List<Long> actualIds = results.stream().map(EquipmentData::getId).collect(Collectors.toList());
        assertTrue(actualIds.contains(expectedEquipment.getId()));
        List<String> actualName = results.stream().map(EquipmentData::getName).collect(Collectors.toList());
        assertTrue(actualName.contains(expectedEquipment.getName()));
        List<LocalDateTime> actualDate = results.stream().map(EquipmentData::getCreateDate).collect(Collectors.toList());
        assertTrue(actualDate.contains(localDateTime));
    }
    @Test
    public void update(){
        // GIVEN
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        UserData user = userService.registerUser(new UserData("Jan", "qwerty", false, "PL", new ArrayList<>(), localDateTime));
        EquipmentData equipment = new EquipmentData(user,"Car", localDateTime);
        AttributeData attribute = attributeService.create(new AttributeData("Mark", "String", "Equipment", localDateTime));
        List<ValueData> values = new ArrayList<>();
        values.add(new ValueData(attribute.getId(), "BMW", "Mark"));
        EquipmentData expectedEquipment = equipmentService.create(equipment, values);
        expectedEquipment.setName("Bike");
        equipmentService.update(expectedEquipment, values);
        // WHEN
        List<EquipmentData> results = equipmentService.findAll();
        // EXPECTED
        assertEquals(1, results.size());
        List<Long> actualIds = results.stream().map(EquipmentData::getId).collect(Collectors.toList());
        assertTrue(actualIds.contains(expectedEquipment.getId()));
        List<String> actualName = results.stream().map(EquipmentData::getName).collect(Collectors.toList());
        assertTrue(actualName.contains(expectedEquipment.getName()));
        assertFalse(actualName.contains(equipment.getName()));
        List<LocalDateTime> actualDate = results.stream().map(EquipmentData::getCreateDate).collect(Collectors.toList());
        assertTrue(actualDate.contains(localDateTime));


    }
    @Test
    public void get(){
        // GIVEN
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        UserData user = userService.registerUser(new UserData("Jan", "qwerty", false, "PL", new ArrayList<>(), localDateTime));
        EquipmentData equipment = new EquipmentData(user,"Car", localDateTime);
        AttributeData attribute = attributeService.create(new AttributeData("Mark", "String", "Equipment", localDateTime));
        List<ValueData> values = new ArrayList<>();
        values.add(new ValueData(attribute.getId(), "BMW", "Mark"));
        EquipmentData expectedEquipment = equipmentService.create(equipment, values);
        // WHEN
        EquipmentsWithDetailsData actualEquipment = equipmentService.get(expectedEquipment.getId());
        // EXPECTED
        assertEquals(expectedEquipment.getId(), actualEquipment.getEquipment().getId());
        assertEquals(expectedEquipment.getName(), actualEquipment.getEquipment().getName());
        assertEquals(expectedEquipment.getCreateDate().truncatedTo(ChronoUnit.SECONDS), actualEquipment.getEquipment().getCreateDate().truncatedTo(ChronoUnit.SECONDS));
    }
    @Test
    public void findAll(){
        // GIVEN
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        UserData user = userService.registerUser(new UserData("Jan", "qwerty", false, "PL", new ArrayList<>(), localDateTime));
        AttributeData attribute = attributeService.create(new AttributeData("Mark", "String", "Equipment", localDateTime));
        List<ValueData> values = new ArrayList<>();
        values.add(new ValueData(attribute.getId(), "BMW", "Mark"));
        List<EquipmentData> equipments = equipmentService.findAll();
        equipments.add(new EquipmentData(user,"Car", localDateTime));
        equipments.add(new EquipmentData(user,"Bike", localDateTime));
        Map<Long, EquipmentData> expectedEquipment = new HashMap<>();
        equipments.forEach(equipmentData -> {
            EquipmentData expected = equipmentService.create(equipmentData, values);
            expectedEquipment.put(expected.getId(), expected);
        });
        // WHEN
        List<EquipmentData> results = equipmentService.findAll();
        Map<Long, EquipmentData> actualEquipment = new HashMap<>();
        results.forEach(equipmentData -> {
            actualEquipment.put(equipmentData.getId(), equipmentData);
        });
        // EXPECTED
        assertEquals(2, actualEquipment.size());
        for (Map.Entry<Long, EquipmentData> entry : expectedEquipment.entrySet()) {
            EquipmentData actual = actualEquipment.get(entry.getKey());
            EquipmentData expexted = entry.getValue();
            assertEquals(expexted.getId(), actual.getId());
            assertEquals(expexted.getName(), actual.getName());
            assertEquals(expexted.getCreateDate().truncatedTo(ChronoUnit.SECONDS), actual.getCreateDate().truncatedTo(ChronoUnit.SECONDS));
            assertFalse(actual.getName().contains("Bus"));

        }
    }
    @Test
    public void remove(){
        // GIVEN
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        UserData user = userService.registerUser(new UserData("Jan", "qwerty", false, "PL", new ArrayList<>(), localDateTime));
        EquipmentData majorEquipment = new EquipmentData(user,"Car", localDateTime);
        EquipmentData equipment = new EquipmentData(user,"Bike", localDateTime);
        AttributeData attribute = attributeService.create(new AttributeData("Mark", "String", "Equipment", localDateTime));
        List<ValueData> values = new ArrayList<>();
        values.add(new ValueData(attribute.getId(), "BMW", "Mark"));
        EquipmentData majorExpectedEquipment = equipmentService.create(majorEquipment, values);
        EquipmentData expectedEquipment = equipmentService.create(equipment, values);
        // WHEN
        equipmentService.remove(majorExpectedEquipment.getId());
        List<EquipmentData> results = equipmentService.findAll();
        // EXPECTED
        assertEquals(1, results.size());
        List<Long> actualIds = results.stream().map(EquipmentData::getId).collect(Collectors.toList());
        assertFalse(actualIds.contains(majorExpectedEquipment.getId()));
        assertTrue(actualIds.contains(expectedEquipment.getId()));
        List<String> actualName = results.stream().map(EquipmentData::getName).collect(Collectors.toList());
        assertFalse(actualName.contains(majorExpectedEquipment.getName()));
        assertTrue(actualName.contains(expectedEquipment.getName()));
    }
    @Test
    public void findByLogin(){
        // GIVEN
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        UserData user = userService.registerUser(new UserData("Jan", "qwerty", false, "PL", new ArrayList<>(), localDateTime));
        UserData student = userService.registerUser(new UserData("Piotr", "qwerty", false, "PL", new ArrayList<>(), localDateTime));
        EquipmentData majorEquipment = new EquipmentData(user,"Car", localDateTime);
        EquipmentData equipment = new EquipmentData(student,"Bike", localDateTime);
        AttributeData attribute = attributeService.create(new AttributeData("Mark", "String", "Equipment", localDateTime));
        List<ValueData> values = new ArrayList<>();
        values.add(new ValueData(attribute.getId(), "BMW", "Mark"));
        EquipmentData majorExpectedEquipment = equipmentService.create(majorEquipment, values);
        EquipmentData expectedEquipment = equipmentService.create(equipment, values);
        // WHEN
        List<EquipmentData> results = equipmentService.findByLogin(majorExpectedEquipment.getUserData().getLogin());
        // EXPECTED
        assertEquals(1, results.size());
        List<Long> actualIds = results.stream().map(EquipmentData::getId).collect(Collectors.toList());
        assertTrue(actualIds.contains(majorExpectedEquipment.getId()));
        List<String> actualName = results.stream().map(EquipmentData::getName).collect(Collectors.toList());
        assertTrue(actualName.contains(majorExpectedEquipment.getName()));
        List<LocalDateTime> actualDate = results.stream().map(EquipmentData::getCreateDate).collect(Collectors.toList());
        assertTrue(actualDate.contains(localDateTime));
    }
}