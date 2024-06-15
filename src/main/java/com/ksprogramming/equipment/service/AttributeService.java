package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.AssignedAttributeData;
import com.ksprogramming.equipment.data.AttributeData;
import com.ksprogramming.equipment.entities.AssignedAttribute;
import com.ksprogramming.equipment.entities.Attribute;
import com.ksprogramming.equipment.enumes.AttributeType;
import com.ksprogramming.equipment.enumes.DomainType;
import com.ksprogramming.equipment.repository.AssignedAttributeRepository;
import com.ksprogramming.equipment.repository.AttributeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AttributeService implements AttributeServiceInterface{
    private AttributeRepository attributeRepository;
    private AssignedAttributeRepository assignedAttributeRepository;

    private static final Logger log = LoggerFactory.getLogger(AttributeService.class);
    public AttributeService(AttributeRepository attributeRepository, AssignedAttributeRepository assignedAttributeRepository) {
        this.attributeRepository = attributeRepository;
        this.assignedAttributeRepository = assignedAttributeRepository;

    }

    public List<AssignedAttributeData> findAttributesWithValue(Long id) {
        return assignedAttributesEntityToData(assignedAttributeRepository.getAttributeWithValue(id));
    }

    public List<AttributeData> findAttributes() {
        List<Attribute> attributes = attributeRepository.findAllAttributes();
        return attributesEntityToData(attributes);
    }

    public List<AttributeData> findAttributesByDomain() {
        List<Attribute> attributes = attributeRepository.getAttributeByDomain();
        return attributesEntityToData(attributes);
    }

    public AttributeData getAttribute(Long id) {
        AttributeData attribute = attributeEntityToData(attributeRepository.getReferenceById(id.intValue()));
        return attribute;
    }

    public void delete(Long id) {
        Attribute attributeEntity = attributeRepository.getReferenceById(id.intValue());
        attributeEntity.setRemoveDate(LocalDateTime.now());
        attributeRepository.save(attributeEntity);
    }

    public void update(AttributeData attributeData) {
        AttributeData attribute = attributeEntityToData(attributeRepository.getReferenceById(attributeData.getId().intValue()));
        attribute.setName(attributeData.getName());
        attribute.setType(attributeTypeByName(attributeData.getType()));
        attribute.setDomain(domainTypeByName(attributeData.getDomain()));
        attribute.setEditDate(LocalDateTime.now());
        validate(attributeData);
        validateUniqueName(attributeData);
        List<AssignedAttributeData> assignedAttributesDataList = assignedAttributesEntityToData(assignedAttributeRepository.getAttributeWithValue(attribute.getId()));
        assignedAttributesDataList.stream()
                .forEach(assignedAttributeData -> {
                    if (!assignedAttributeData.getAttribute().getType().equals(attribute.getType())) {
                        if (!assignedAttributeData.getValue().equals(null)) {
                            throw new IllegalArgumentException("Couldn't change type. Attribute is used in another place");
                        }
                    }
                });
        attributeRepository.save(attributeDataToEntity(attribute));
    }

    public AttributeData create(AttributeData attributeData) {
        attributeData.setType(attributeTypeByName(attributeData.getType()));
        attributeData.setDomain(domainTypeByName(attributeData.getDomain()));
        validate(attributeData);
        validateUniqueName(attributeData);
        Attribute attributeEntity = attributeRepository.save(attributeDataToEntity(attributeData));
        attributeData.setId(attributeEntity.getId());
        return attributeData;
    }
    private void validate(AttributeData attributeData) {
        if (attributeData.getName().length() > 199){
            throw new IllegalArgumentException("Name to long. Maximum lenght 200 characters");
        }
    }
    private void validateUniqueName(AttributeData attributeData){
        List<AttributeData> attributesData = attributesEntityToData(attributeRepository.getAttributeByDomain());
        attributesData.stream()
                .forEach(attribute -> {
                    if (attribute.getId() != attributeData.getId()) {
                        if (attribute.getName().equals(attributeData.getName())) {
                            throw new IllegalArgumentException("That name was used. Use unique name");
                        }
                    }
                });
    }



    private List<AttributeData> attributesEntityToData(List<Attribute> attributes) {
        List<AttributeData> attributeData = new ArrayList<>();
        attributes.stream().forEach(attributeEntity -> {
            attributeData.add(new AttributeData(attributeEntity.getId(), attributeEntity.getName(),
                    AttributeType.valueOf(attributeEntity.getType()).getName(), DomainType.valueOf(attributeEntity.getDomain()).getCode(),
                    assignedAttributesEntityToData(attributeEntity.getAssignedAttributes()),
                    attributeEntity.getCreateDate(), attributeEntity.getEditDate(), attributeEntity.getRemoveDate()));
        });
        return attributeData;
    }

    private String attributeTypeByName(String attributeType) {
        for (AttributeType type : AttributeType.values()) {
            if (type.getName().equals(attributeType)) {
                return type.toString();
            }
        }
        return null;
    }

    private String domainTypeByName(String domainType) {
        for (DomainType type : DomainType.values()) {
            if (type.getCode().equals(domainType)) {
                return type.toString();
            }
        }
        return null;
    }

    private AttributeData attributeEntityToData(Attribute attribute) {
        return new AttributeData(attribute.getId(), attribute.getName(), attribute.getType(), attribute.getDomain()
                , assignedAttributesEntityToData(attribute.getAssignedAttributes()), attribute.getCreateDate(), attribute.getEditDate(), attribute.getRemoveDate());
    }

    private Attribute attributeDataToEntity(AttributeData attribute) {
        return new Attribute(attribute);
    }

    private List<AssignedAttributeData> assignedAttributesEntityToData(List<AssignedAttribute> assignedAttributes) {
        List<AssignedAttributeData> list = new ArrayList<>();
        assignedAttributes.stream()
                .forEach(assignedAttribute -> {
                    list.add(new AssignedAttributeData(assignedAttribute.getId(), assignedAttribute.getDomain(), assignedAttribute.getDomainId(),
                            assignedAttribute.getValue(), assignedAttribute.getCreateDate(),
                            assignedAttribute.getEditDate(), assignedAttribute.getRemoveDate()));
                });
        return list;
    }
}
