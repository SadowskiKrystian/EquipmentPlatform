package com.ksprogramming.equipment.data;

import com.ksprogramming.equipment.api.ValuePutRequest;

public class ValueData {
    private Long id;
    private String valuee;
    private String attributeName;
    private Long assignedAttributeId;

    public ValueData() {
    }

    public ValueData(Long id, String valuee, String attributeName) {
        this.id = id;
        this.valuee = valuee;
        this.attributeName = attributeName;
    }
    public ValueData(ValuePutRequest request){
        this.id = request.getId();
        this.valuee = request.getValue();
        this.attributeName = request.getAttributeName();
        this.assignedAttributeId = request.getAssignedAttributeId();
    }


    public Long getId() {
        return id;
    }

    public String getValuee() {
        return valuee;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public Long getAssignedAttributeId() {
        return assignedAttributeId;
    }

}
