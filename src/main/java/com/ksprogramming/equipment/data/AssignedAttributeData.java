package com.ksprogramming.equipment.data;

import java.time.LocalDateTime;

public class AssignedAttributeData {
    private Long id;
    private String domain;

    private Long domainId;

    private AttributeData attributeData;
    private String value;


    private LocalDateTime createDate;

    private LocalDateTime editDate;

    private LocalDateTime removeDate;

    public AssignedAttributeData() {
    }

    public AssignedAttributeData(String domain, Long domainId, AttributeData attribute, String value, LocalDateTime createDate) {
        this.domain = domain;
        this.domainId = domainId;
        this.attributeData = attribute;
        this.value = value;
        this.createDate = createDate;
    }

    public AssignedAttributeData(String domain, Long domainId, AttributeData attribute, String value, LocalDateTime createDate, LocalDateTime editDate, LocalDateTime removeDate) {
        this.domain = domain;
        this.domainId = domainId;
        this.attributeData = attribute;
        this.value = value;
        this.createDate = createDate;
        this.editDate = editDate;
        this.removeDate = removeDate;
    }

    public AssignedAttributeData(Long id, String domain, Long domainId, String value, LocalDateTime createDate, LocalDateTime editDate, LocalDateTime removeDate) {
        this.id = id;
        this.domain = domain;
        this.domainId = domainId;
        this.value = value;
        this.createDate = createDate;
        this.editDate = editDate;
        this.removeDate = removeDate;
    }
    public AssignedAttributeData(Long id,  String domain, Long domainId, AttributeData attributeData, String value, LocalDateTime createDate, LocalDateTime editDate, LocalDateTime removeDate) {
        this.id = id;
        this.domain = domain;
        this.domainId = domainId;
        this.attributeData = attributeData;
        this.value = value;
        this.createDate = createDate;
        this.editDate = editDate;
        this.removeDate = removeDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(LocalDateTime editDate) {
        this.editDate = editDate;
    }

    public LocalDateTime getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(LocalDateTime removeDate) {
        this.removeDate = removeDate;
    }

    public AttributeData getAttribute() {
        return attributeData;
    }

    public void setAttribute(AttributeData attribute) {
        this.attributeData = attribute;
    }

}
