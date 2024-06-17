package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.AssignedAttributeData;
import com.ksprogramming.equipment.data.AttributeData;
import com.ksprogramming.equipment.repository.AssignedAttributeRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-integrationtest.properties")

public class AssignedAttributeServiceTest {
    @Autowired
    private AssignedAttributeService assignedAttributeService;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private AssignedAttributeRepository assignedAttributeRepository;
    @Before
    public void setUp(){
        assignedAttributeRepository.deleteAll();
    }
    @Test
    public void createTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        AttributeData attributeData = attributeService.create(new AttributeData("Mark", "String", "Equipment", localDateTime));
        AssignedAttributeData assignedAttributeData = assignedAttributeService.save(new AssignedAttributeData("Equipment", 1L, attributeData, "BMW", localDateTime));
        List<AssignedAttributeData> attributesWithValue = assignedAttributeService.getAttributeWithValues(assignedAttributeData.getAttribute().getId());
        List<String> assignedAttributeValues = attributesWithValue.stream().map(AssignedAttributeData::getValue).collect(Collectors.toList());
        assertTrue(assignedAttributeValues.contains("BMW"));
        List<String> assignedAttributeDomains = attributesWithValue.stream().map(AssignedAttributeData::getDomain).collect(Collectors.toList());
        assertTrue(assignedAttributeDomains.contains("Equipment"));
    }
    @Test
    public void getAttributeTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        AttributeData attributeData = attributeService.create(new AttributeData("Mark", "String", "Equipment", localDateTime));
        AttributeData secondAttributeData = attributeService.create(new AttributeData("Model", "String", "Equipment", localDateTime));
        AssignedAttributeData assignedAttributeDataBmw = assignedAttributeService.save(new AssignedAttributeData("Equipment", 1L, attributeData, "BMW", localDateTime));
        AssignedAttributeData assignedAttributeDataPorsche = assignedAttributeService.save(new AssignedAttributeData("Equipment", 2L, attributeData, "Porsche", localDateTime));
        AssignedAttributeData assignedAttributeDataAudi = assignedAttributeService.save(new AssignedAttributeData("Equipment", 3L, secondAttributeData, "Audi", localDateTime));
        AssignedAttributeData assignedAttributeDataMercedes = assignedAttributeService.save(new AssignedAttributeData("Equipment", 4L, secondAttributeData, "Mercedes", localDateTime));
        List<AssignedAttributeData> results = assignedAttributeService.getAttributeWithValues(assignedAttributeDataBmw.getAttribute().getId());
        List<String> resultByDomain = results.stream().map(AssignedAttributeData::getDomain).collect(Collectors.toList());
        assertTrue(resultByDomain.contains("Equipment"));
        assertFalse(resultByDomain.contains("Customer"));
        List<String> resultsByValue = results.stream().map(AssignedAttributeData::getValue).collect(Collectors.toList());
        assertTrue(resultsByValue.contains("BMW"));
        assertTrue(resultsByValue.contains("Porsche"));
        assertFalse(resultsByValue.contains("Mercedes"));
    }
    @Test
    public void findAllTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        AttributeData attributeData = attributeService.create(new AttributeData("Mark", "String", "Equipment", localDateTime));
        AttributeData secondAttributeData = attributeService.create(new AttributeData("Model", "String", "Equipment", localDateTime));
        List<AssignedAttributeData> assignedAttributes = new ArrayList<>();
        assignedAttributes.add(new AssignedAttributeData("Equipment", 1L, attributeData, "BMW", localDateTime));
        assignedAttributes.add(new AssignedAttributeData("Equipment", 1L, attributeData, "Romet", localDateTime));
        assignedAttributes.add(new AssignedAttributeData("Equipment", 1L, secondAttributeData, "Kia", localDateTime));
        assignedAttributes.add(new AssignedAttributeData("Equipment", 1L, secondAttributeData, "Opel", localDateTime));
        Map<Long, AssignedAttributeData> assignedAtrributesById = new HashMap<>();
        assignedAttributes.stream().forEach(assignedAttributeData -> {
            AssignedAttributeData assignedAttribute = assignedAttributeService.save(assignedAttributeData);
            assignedAtrributesById.put(assignedAttribute.getId(), assignedAttribute);
        });
        List<AssignedAttributeData> results = assignedAttributeService.findAll();
        Map<Long, AssignedAttributeData> saveAssignedAttributesById = new HashMap<>();
        results.stream().forEach(result -> {
            saveAssignedAttributesById.put(result.getId(), result);
        });

        for (Map.Entry<Long, AssignedAttributeData> assignedAttributeById : assignedAtrributesById.entrySet()) {
            AssignedAttributeData actual = saveAssignedAttributesById.get(assignedAttributeById.getKey());
            AssignedAttributeData expected = assignedAttributeById.getValue();
            assertEquals(expected.getId(), actual.getId());
            assertEquals(expected.getDomain(), actual.getDomain());
            assertEquals(expected.getDomainId(), actual.getDomainId());
            assertEquals(expected.getAttribute().getId(), actual.getAttribute().getId());
            assertEquals(expected.getAttribute().getName(), actual.getAttribute().getName());
            assertEquals(expected.getValue(), actual.getValue());
            assertEquals(expected.getCreateDate().truncatedTo(ChronoUnit.SECONDS), actual.getCreateDate().truncatedTo(ChronoUnit.SECONDS));
        }
    }
}