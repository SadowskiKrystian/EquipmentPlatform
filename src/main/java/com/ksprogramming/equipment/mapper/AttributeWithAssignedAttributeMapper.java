package com.ksprogramming.equipment.mapper;

import com.ksprogramming.equipment.data.AttributeData;
import com.ksprogramming.equipment.entities.AssignedAttribute;
import com.ksprogramming.equipment.entities.Attribute;

public class AttributeWithAssignedAttributeMapper {
    public static AttributeData entityToData(Attribute attribute, AssignedAttribute assignedAttribute) {
        return new AttributeData(attribute, assignedAttribute);
    }
}
