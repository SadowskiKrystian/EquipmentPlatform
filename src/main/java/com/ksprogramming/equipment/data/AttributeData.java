package com.ksprogramming.equipment.data;

import com.ksprogramming.equipment.entities.AssignedAttribute;
import com.ksprogramming.equipment.entities.Attribute;

import java.time.LocalDateTime;
import java.util.List;

public class AttributeData {
    private Long id;
    private String name;
    private String type;
    private String domain;
    private List<AssignedAttributeData> assignedAttributesData;
    private LocalDateTime createDate;
    private LocalDateTime editDate;
    private LocalDateTime removeDate;
    private Long valueId;
    private String value;
    private Long assignedAttributeId;

    public AttributeData(Attribute attribute) {
        this.id = attribute.getId();
        this.name = attribute.getName();
        this.domain = attribute.getDomain();
        this.type = attribute.getType();
        this.createDate = attribute.getCreateDate();
        this.editDate = attribute.getEditDate();
        this.removeDate = attribute.getRemoveDate();
    }

    public AttributeData(Long id, String name, String type, String domain, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.domain = domain;
        this.createDate = createDate;
    }

    public AttributeData(Attribute attribute, AssignedAttribute assignedAttribute) {
        this.id = attribute.getId();
        this.name = attribute.getName();
        this.domain = attribute.getDomain();
        this.type = attribute.getType();
        this.createDate = attribute.getCreateDate();
        this.editDate = attribute.getEditDate();
        this.removeDate = attribute.getRemoveDate();
        this.valueId = attribute.getId();
        this.value = assignedAttribute.getValue();
        this.assignedAttributeId = assignedAttribute.getId();
    }

    public AttributeData(Long id, String name, String type, String domain, LocalDateTime createDate, LocalDateTime editDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.domain = domain;
        this.createDate = createDate;
        this.editDate = editDate;
    }

    public AttributeData(Long id, String name, String type, String domain, List<AssignedAttributeData> assignedAttributesData, LocalDateTime createDate, LocalDateTime editDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.domain = domain;
        this.assignedAttributesData = assignedAttributesData;
        this.createDate = createDate;
        this.editDate = editDate;
    }

    public AttributeData(Long id, String name, String type, String domain) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.domain = domain;
    }

    public AttributeData(Long id, String name, String type, String domain, List<AssignedAttributeData> assignedAttributesData, LocalDateTime createDate, LocalDateTime editDate, LocalDateTime removeDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.domain = domain;
        this.assignedAttributesData = assignedAttributesData;
        this.createDate = createDate;
        this.editDate = editDate;
        this.removeDate = removeDate;
    }

    public AttributeData(String name, String type, String domain, LocalDateTime createDate) {
        this.name = name;
        this.type = type;
        this.domain = domain;
        this.createDate = createDate;
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

    public String getDomain() {
        return domain;
    }

    public List<AssignedAttributeData> getAssignedAttributesData() {
        return assignedAttributesData;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }

    public LocalDateTime getRemoveDate() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setAssignedAttributesData(List<AssignedAttributeData> assignedAttributesData) {
        this.assignedAttributesData = assignedAttributesData;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setEditDate(LocalDateTime editDate) {
        this.editDate = editDate;
    }

    public void setRemoveDate(LocalDateTime removeDate) {
        this.removeDate = removeDate;
    }

    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setAssignedAttributeId(Long assignedAttributeId) {
        this.assignedAttributeId = assignedAttributeId;
    }

}
