package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.AssignedAttributeData;

import java.util.List;

public interface AssignedAttributeServiceInterface {
    AssignedAttributeData save(AssignedAttributeData assignedAttribute);

    List<AssignedAttributeData> getAttributeWithValues(Long id);

    List<AssignedAttributeData> findAll();

}
