package com.ksprogramming.equipment.api;

import com.ksprogramming.equipment.data.AssignedAttributeData;
import com.ksprogramming.equipment.data.AttributeData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AssignedAttributeGetResponse {
    private Long id;
    private String name;
    private String domain;
    private Long domainId;
    private Long attributeId;
    private String type;

    private String createDate;

    private String editDate;

    private String removeDate;
    private Long valueId;

    private String value;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AssignedAttributeGetResponse(Long id, String name, String type, LocalDateTime createDate, LocalDateTime editDate, LocalDateTime removeDate, Long valueId, String value) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createDate = createDate.format(FORMATTER);
        this.editDate = editDate != null ? editDate.format(FORMATTER) : null;
        this.removeDate = removeDate != null ? removeDate.format(FORMATTER) : null;
        this.valueId = valueId;
        this.value = value;
    }

    public AssignedAttributeGetResponse(AttributeData attributeData) {
        this.id = attributeData.getId();
        this.name = attributeData.getName();
        this.type = attributeData.getType();
        this.createDate = attributeData.getCreateDate() != null ? attributeData.getCreateDate().format(FORMATTER) : null;
        this.editDate = attributeData.getEditDate() != null ? attributeData.getEditDate().format(FORMATTER) : null;
        this.removeDate = attributeData.getRemoveDate() != null ? attributeData.getRemoveDate().format(FORMATTER) : null;
        this.valueId = attributeData.getValueId();
        this.value = attributeData.getValue();
    }

    public AssignedAttributeGetResponse(AssignedAttributeData assignedAttributeData){
        this.id = assignedAttributeData.getId();
        this.domain = assignedAttributeData.getDomain();
        this.domainId = assignedAttributeData.getDomainId();
        this.attributeId = assignedAttributeData.getAttribute().getId();
        this.value = assignedAttributeData.getValue();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public Long getDomainId() {
        return domainId;
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

    public Long getAttributeId() {
        return attributeId;
    }
}

