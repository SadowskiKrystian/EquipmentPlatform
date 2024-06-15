package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.AssignedAttributeData;
import com.ksprogramming.equipment.data.AttributeData;

import java.util.List;

public interface AttributeServiceInterface {
    List<AssignedAttributeData> findAttributesWithValue(Long id);
    List<AttributeData> findAttributes();
    List<AttributeData> findAttributesByDomain();
    AttributeData getAttribute(Long id);
    void delete(Long id);
    void update(AttributeData attributeData);
    AttributeData create(AttributeData attributeData);

}
