package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.AssignedAttributeData;
import com.ksprogramming.equipment.data.AttributeData;
import com.ksprogramming.equipment.entities.AssignedAttribute;
import com.ksprogramming.equipment.entities.Attribute;
import com.ksprogramming.equipment.repository.AssignedAttributeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignedAttributeService implements AssignedAttributeServiceInterface{
    private AssignedAttributeRepository assignedAttributeRepository;

    public AssignedAttributeService(AssignedAttributeRepository assignedAttributeRepository) {
        this.assignedAttributeRepository = assignedAttributeRepository;
    }

    public AssignedAttributeData save(AssignedAttributeData assignedAttribute) {
        return assignedAttributeEntityToData(assignedAttributeRepository.save(assignedAttributeDataToEntity(assignedAttribute)));
    }

    public List<AssignedAttributeData> getAttributeWithValues(Long id) {
        List<AssignedAttribute> assignedAttributes = assignedAttributeRepository.getAttributeWithValue(id);
        return assignedAttributesEntityToData(assignedAttributes);
    }
    public List<AssignedAttributeData> findAll(){
        return assignedAttributesEntityToData(assignedAttributeRepository.findAll());
    }

    private AssignedAttribute assignedAttributeDataToEntity(AssignedAttributeData assignedAttributeData) {
        return new AssignedAttribute(assignedAttributeData.getId(), assignedAttributeData.getDomain(), assignedAttributeData.getDomainId(),
                attributeDataToEntity(assignedAttributeData.getAttribute()), assignedAttributeData.getValue(), assignedAttributeData.getCreateDate(),
                assignedAttributeData.getEditDate(), assignedAttributeData.getRemoveDate());
    }

    private List<AssignedAttributeData> assignedAttributesEntityToData(List<AssignedAttribute> assignedAttributeList) {
        List<AssignedAttributeData> assignedAttributes = new ArrayList<>();
        assignedAttributeList.stream()
                .forEach(assignedAttributeEntity -> {
                    assignedAttributes.add(new AssignedAttributeData(assignedAttributeEntity.getId(), assignedAttributeEntity.getDomain(), assignedAttributeEntity.getDomainId(),
                            attributeEntityToData(assignedAttributeEntity.getAttribute()), assignedAttributeEntity.getValue(), assignedAttributeEntity.getCreateDate(),
                            assignedAttributeEntity.getEditDate(), assignedAttributeEntity.getRemoveDate()));
                });
        return assignedAttributes;
    }
    private AssignedAttributeData assignedAttributeEntityToData(AssignedAttribute assignedAttribute){
        return new AssignedAttributeData(assignedAttribute.getId(), assignedAttribute.getDomain(), assignedAttribute.getDomainId(),
                attributeEntityToData(assignedAttribute.getAttribute()), assignedAttribute.getValue(), assignedAttribute.getCreateDate(),
                assignedAttribute.getEditDate(), assignedAttribute.getRemoveDate());
    }
    private AttributeData attributeEntityToData(Attribute attribute){
        return new AttributeData(attribute);
    }
    private Attribute attributeDataToEntity(AttributeData attributeData){
        return new Attribute(attributeData);
    }

}
