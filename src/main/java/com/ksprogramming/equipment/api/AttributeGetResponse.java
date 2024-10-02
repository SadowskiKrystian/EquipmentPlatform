package com.ksprogramming.equipment.api;

import com.ksprogramming.equipment.data.AttributeData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AttributeGetResponse {
    private Long id;
    private String name;
    private String type;
    private String domain;
    private String createDate;
    private String editDate;
    private String removeDate;
    private Long valueId;
    private String value;
    private Long assignedAttributeId;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AttributeGetResponse(Long id, String name, String type, String domain, LocalDateTime createDate,
                                LocalDateTime editDate, LocalDateTime removeDate, Long valueId, String value, Long assignedAttributeId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.domain = domain;
        this.createDate = createDate.format(FORMATTER);
        this.editDate = editDate != null? editDate.format(FORMATTER): null;
        this.removeDate = removeDate != null? removeDate.format(FORMATTER): null;
        this.valueId = valueId;
        this.value = value;
        this.assignedAttributeId = assignedAttributeId;
    }

    public AttributeGetResponse(Long id, String name, String type, LocalDateTime createDate, LocalDateTime editDate, LocalDateTime removeDate, Long valueId, String value) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createDate = createDate.format(FORMATTER);
        this.editDate = editDate != null? editDate.format(FORMATTER): null;
        this.removeDate = removeDate != null? removeDate.format(FORMATTER): null;
        this.valueId = valueId;
        this.value = value;
    }
    public AttributeGetResponse(AttributeData attributeData){
        this.id = attributeData.getId();
        this.name = attributeData.getName();
        this.type = attributeData.getType();
        this.domain = attributeData.getDomain();
        this.createDate = attributeData.getCreateDate().format(FORMATTER);
        this.editDate = attributeData.getEditDate() != null? attributeData.getEditDate().format(FORMATTER): "";
        this.removeDate = attributeData.getRemoveDate() != null? attributeData.getRemoveDate().format(FORMATTER): "";
        this.valueId = attributeData.getValueId();
        this.value = attributeData.getValue();
        this.assignedAttributeId = attributeData.getAssignedAttributeId();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getEditDate() {
        return editDate;
    }

    public String getRemoveDate() {
        return removeDate;
    }

    public Long getValueId() {
        return valueId;
    }

    public String getValue() {
        return value;
    }

    public Long getAssignedAttributeId() {
        return assignedAttributeId;
    }

    public String getDomain() {
        return domain;
    }

    public static AttributeGetResponseBuilder builder(){
        return new AttributeGetResponseBuilder();
    }

    public static class AttributeGetResponseBuilder {
        private Long id;
        private String name;
        private String type;
        private String domain;
        private LocalDateTime createDate;
        private LocalDateTime editDate;
        private LocalDateTime removeDate;
        private Long valueId;
        private String value;
        private Long assignedAttributeId;

        public AttributeGetResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AttributeGetResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AttributeGetResponseBuilder type(String type) {
            this.type = type;
            return this;
        }

        public AttributeGetResponseBuilder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public AttributeGetResponseBuilder createDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public AttributeGetResponseBuilder editDate(LocalDateTime editDate) {
            this.editDate = editDate;
            return this;
        }

        public AttributeGetResponseBuilder removeDate(LocalDateTime removeDate) {
            this.removeDate = removeDate;
            return this;
        }

        public AttributeGetResponseBuilder valueId(Long valueId) {
            this.valueId = valueId;
            return this;
        }

        public AttributeGetResponseBuilder value(String value) {
            this.value = value;
            return this;
        }

        public AttributeGetResponseBuilder assignedAttributeId(Long assignedAttributeId) {
            this.assignedAttributeId = assignedAttributeId;
            return this;
        }

        public AttributeGetResponse build() {
            return new AttributeGetResponse(id, name, type, domain, createDate, editDate, removeDate, valueId, value, assignedAttributeId);
        }
    }
}
