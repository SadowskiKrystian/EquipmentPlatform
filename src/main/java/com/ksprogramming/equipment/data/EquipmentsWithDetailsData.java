package com.ksprogramming.equipment.data;

import java.util.List;

public class EquipmentsWithDetailsData {
    private EquipmentData equipment;
    private List<AttributeData> attributes;

    public EquipmentsWithDetailsData(EquipmentData equipment, List<AttributeData> attributes) {
        this.equipment = equipment;
        this.attributes = attributes;
    }

    public EquipmentData getEquipment() {
        return equipment;
    }

    public List<AttributeData> getAttributes() {
        return attributes;
    }

}
