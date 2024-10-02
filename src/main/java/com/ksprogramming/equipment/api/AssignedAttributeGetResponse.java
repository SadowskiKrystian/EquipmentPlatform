package com.ksprogramming.equipment.api;

import com.ksprogramming.equipment.data.AssignedAttributeData;
import com.ksprogramming.equipment.data.AttributeData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AssignedAttributeGetResponse {
    private Long id;
    private String domain;
    private Long domainId;
    private Long attributeId;
    private String value;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public AssignedAttributeGetResponse(AssignedAttributeData assignedAttributeData){
        this.id = assignedAttributeData.getId();
        this.domain = assignedAttributeData.getDomain();
        this.domainId = assignedAttributeData.getDomainId();
        this.attributeId = assignedAttributeData.getAttribute().getId();
        this.value = assignedAttributeData.getValue();
    }

    public AssignedAttributeGetResponse(Long id, String domain, Long domainId, Long attributeId, String value) {
        this.id = id;
        this.domain = domain;
        this.domainId = domainId;
        this.attributeId = attributeId;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getDomain() {
        return domain;
    }

    public Long getDomainId() {
        return domainId;
    }

    public String getValue() {
        return value;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public static AssignedAttributeGetResponseBuilder builder(){
        return new AssignedAttributeGetResponseBuilder();
    }

    public static class AssignedAttributeGetResponseBuilder {
        private Long id;
        private String domain;
        private Long domainId;
        private Long attributeId;
        private String value;

        public AssignedAttributeGetResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AssignedAttributeGetResponseBuilder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public AssignedAttributeGetResponseBuilder domainId(Long domainId) {
            this.domainId = domainId;
            return this;
        }

        public AssignedAttributeGetResponseBuilder attributeId(Long attributeId) {
            this.attributeId = attributeId;
            return this;
        }

        public AssignedAttributeGetResponseBuilder value(String value) {
            this.value = value;
            return this;
        }

        public AssignedAttributeGetResponse build() {
            return new AssignedAttributeGetResponse(id, domain, domainId, attributeId, value);
        }
    }

}

