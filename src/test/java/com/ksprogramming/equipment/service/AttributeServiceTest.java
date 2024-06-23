package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.AttributeData;
import com.ksprogramming.equipment.repository.AttributeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(
        value = {SpringExtension.class}
)
@TestPropertySource("classpath:application-integrationtest.properties")
public class AttributeServiceTest {
    @Autowired
    private AttributeServiceInterface attributeService;
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private AssignedAttributeService assignedAttributeService;
    @Before
    public void init(){
        attributeRepository.deleteAll();
    }

    @Test
    public void createUserAuthorityTest() {
        LocalDateTime localDateTime = LocalDateTime.now(); // todo poszukac dokladne porownanie
        AttributeData attributeData = attributeService.create(new AttributeData("Dupa", "String", "Equipment", localDateTime));
        Long id = attributeData.getId();
        AttributeData attribute = attributeService.getAttribute(id);
        assertEquals(attribute.getName(), "Dupa");
        assertEquals(attribute.getType(), "STRING");
        assertEquals(attribute.getDomain(), "EQUIPMENT");
        assertEquals(attribute.getCreateDate().truncatedTo(ChronoUnit.SECONDS), localDateTime.truncatedTo(ChronoUnit.SECONDS));
        // todo how to check inferior object
    }
    @Test
    public void checkFindTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        attributeService.create(new AttributeData("Maniek", "String", "Equipment", localDateTime.truncatedTo(ChronoUnit.SECONDS)));
        attributeService.create(new AttributeData("Krowa", "Date", "Equipment", localDateTime.truncatedTo(ChronoUnit.SECONDS)));
        attributeService.create(new AttributeData("Rower", "Time", "Equipment", localDateTime.truncatedTo(ChronoUnit.SECONDS)));
        attributeService.create(new AttributeData("Zupa", "Int", "Equipment", localDateTime.truncatedTo(ChronoUnit.SECONDS)));
        List<AttributeData> results = attributeService.findAttributes();
        assertNotNull(results);
        assertEquals(results.size(), 4);
        Set<String> resultNames = results.stream().map(AttributeData::getName).collect(Collectors.toSet());
        assertTrue(resultNames.contains("Maniek"));
        assertTrue(resultNames.contains("Krowa"));
        assertTrue(resultNames.contains("Rower"));
        assertTrue(resultNames.contains("Zupa"));
        Set<String> resultType = results.stream().map(AttributeData::getType).collect(Collectors.toSet());
        assertTrue(resultType.contains("String"));
        assertTrue(resultType.contains("Date"));
        assertTrue(resultType.contains("Time"));
        assertTrue(resultType.contains("Int"));
        Set<String> resultDomain    = results.stream().map(AttributeData::getDomain).collect(Collectors.toSet());
        assertTrue(resultDomain.contains("Equipment"));
        Set<LocalDateTime> resultCreateDate = results.stream().map(AttributeData::getCreateDate).collect(Collectors.toSet());
        assertTrue(resultCreateDate.contains(localDateTime.truncatedTo(ChronoUnit.SECONDS)));

    }
    @Test
    public void deleteAttributeTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        Long id = attributeService.create(new AttributeData("Maniek", "String", "Equipment", localDateTime)).getId();
        attributeService.delete(id);
        AttributeData result = attributeService.getAttribute(id);
        assertNotNull(result.getRemoveDate());
    }
    @Test
    public void updateAttributeTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        AttributeData attributeData = attributeService.create(new AttributeData("Maciek", "String", "Equipment", localDateTime));
        attributeData.setName("Jurek");
        attributeService.update(attributeData);
        AttributeData updateAttribute = attributeService.getAttribute(attributeData.getId());
        assertEquals("Jurek", updateAttribute.getName());
    }


}