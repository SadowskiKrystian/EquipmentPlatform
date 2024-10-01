package com.ksprogramming.equipment.api;

import java.util.List;

public class EquipmentPutRequest {
    private Long id;
    private String name;
    private List<ValuePutRequest> values;

    public EquipmentPutRequest() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ValuePutRequest> getValues() {
        return values;
    }
}
