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

    public static EquipmentWithDetailsDataBuilder builder(){
        return  new EquipmentWithDetailsDataBuilder();
    }

    public static class EquipmentWithDetailsDataBuilder {
        private EquipmentData equipment;
        private List<AttributeData> attributes;

        public EquipmentWithDetailsDataBuilder equipment(EquipmentData equipment) {
            this.equipment = equipment;
            return this;
        }

        public EquipmentWithDetailsDataBuilder attributes(List<AttributeData> attributes) {
            this.attributes = attributes;
            return this;
        }

        public EquipmentsWithDetailsData build() {
            return new EquipmentsWithDetailsData(equipment, attributes);
        }
    }

}
