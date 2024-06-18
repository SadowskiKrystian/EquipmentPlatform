package com.ksprogramming.equipment.api;

import java.util.List;

public class EquipmentPutRequest {
    private String name;
    private List<ValuePutRequest> values;

    public EquipmentPutRequest() {
    }

    public String getName() {
        return name;
    }

    public List<ValuePutRequest> getValues() {
        return values;
    }
}
