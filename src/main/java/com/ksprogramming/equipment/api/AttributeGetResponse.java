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
}
