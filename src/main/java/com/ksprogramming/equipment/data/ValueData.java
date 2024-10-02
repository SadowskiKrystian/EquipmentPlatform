package com.ksprogramming.equipment.data;

import com.ksprogramming.equipment.api.ValuePutRequest;

public class ValueData {
    private Long id;
    private String valuee;
    private String attributeName;
    private Long assignedAttributeId;

    public ValueData() {
    }

    public ValueData(Long id, String valuee, String attributeName, Long assignedAttributeId) {
        this.id = id;
        this.valuee = valuee;
        this.attributeName = attributeName;
        this.assignedAttributeId = assignedAttributeId;
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

    public static ValueDataBuilder builder(){
        return new ValueDataBuilder();
    }

    public static class ValueDataBuilder {
        private Long id;
        private String valuee;
        private String attributeName;
        private Long assignedAttributeId;

        public ValueDataBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ValueDataBuilder valuee(String valuee) {
            this.valuee = valuee;
            return this;
        }

        public ValueDataBuilder attributeName(String attributeName) {
            this.attributeName = attributeName;
            return this;
        }

        public ValueDataBuilder assignedAttributeId(Long assignedAttributeId) {
            this.assignedAttributeId = assignedAttributeId;
            return this;
        }

        public ValueData build() {
            return new ValueData(id, valuee, attributeName, assignedAttributeId);

        }
    }

}
