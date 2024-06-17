package com.ksprogramming.equipment.api;

import java.util.List;

public class EquipmentPostRequest {
    private String name;
    private List<ValuePostRequest> values;

    public EquipmentPostRequest() {
    }

    public String getName() {
        return name;
    }

    public List<ValuePostRequest> getValues() {
        return values;
    }
}
