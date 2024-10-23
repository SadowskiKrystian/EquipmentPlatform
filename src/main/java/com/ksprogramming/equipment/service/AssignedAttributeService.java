package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.AssignedAttributeData;
import com.ksprogramming.equipment.entities.AssignedAttribute;
import com.ksprogramming.equipment.mapper.AssignedAttributeMapper;
import com.ksprogramming.equipment.repository.AssignedAttributeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignedAttributeService implements AssignedAttributeServiceInterface{
    private AssignedAttributeRepository assignedAttributeRepository;

    public AssignedAttributeService(AssignedAttributeRepository assignedAttributeRepository) {
        this.assignedAttributeRepository = assignedAttributeRepository;
    }

    public AssignedAttributeData save(AssignedAttributeData assignedAttribute) {
        return AssignedAttributeMapper.entityToData(assignedAttributeRepository.save(AssignedAttributeMapper.dataToEntity(assignedAttribute)));
    }

    public List<AssignedAttributeData> getAttributeWithValues(Long id) {
        List<AssignedAttribute> assignedAttributes = assignedAttributeRepository.getAttributeWithValue(id);
        return AssignedAttributeMapper.entitToDataList(assignedAttributes);
    }
    public List<AssignedAttributeData> findAll(){
        return AssignedAttributeMapper.entitToDataList(assignedAttributeRepository.findAll());
    }
}
