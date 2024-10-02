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

    public static AssignedAttributeDataBuilder builder(){
        return new AssignedAttributeDataBuilder();
    }

    public static class AssignedAttributeDataBuilder {
        private Long id;
        private String domain;
        private Long domainId;
        private AttributeData attributeData;
        private String value;
        private LocalDateTime createDate;
        private LocalDateTime editDate;
        private LocalDateTime removeDate;

        public AssignedAttributeDataBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AssignedAttributeDataBuilder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public AssignedAttributeDataBuilder domainId(Long domainId) {
            this.domainId = domainId;
            return this;
        }

        public AssignedAttributeDataBuilder attributeData(AttributeData attributeData) {
            this.attributeData = attributeData;
            return this;
        }

        public AssignedAttributeDataBuilder value(String value) {
            this.value = value;
            return this;
        }

        public AssignedAttributeDataBuilder createDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public AssignedAttributeDataBuilder editDate(LocalDateTime editDate) {
            this.editDate = editDate;
            return this;
        }

        public AssignedAttributeDataBuilder removeDate(LocalDateTime removeDate) {
            this.removeDate = removeDate;
            return this;
        }

        public AssignedAttributeData build() {
            return new AssignedAttributeData(id, domain, domainId, attributeData, value, createDate, editDate, removeDate);
        }
    }

}
