package com.ksprogramming.equipment.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AssignedAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String domain;
    @Column(name = "domain_id")
    private Long domainId;
    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;
    private String value;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "edit_date")
    private LocalDateTime editDate;
    @Column(name = "remove_date")
    private LocalDateTime removeDate;

    public AssignedAttribute() {
    }

    public AssignedAttribute(String domain, Long domainId, String value, LocalDateTime createDate, LocalDateTime editDate, LocalDateTime removeDate) {
        this.domain = domain;
        this.domainId = domainId;
        this.value = value;
        this.createDate = createDate;
        this.editDate = editDate;
        this.removeDate = removeDate;
    }

    public AssignedAttribute(String domain, Long domainId, Attribute attribute, String value, LocalDateTime createDate) {
        this.domain = domain;
        this.domainId = domainId;
        this.attribute = attribute;
        this.value = value;
        this.createDate = createDate;
    }

    public AssignedAttribute(Long id, String domain, Long domainId, Attribute attribute, String value, LocalDateTime createDate, LocalDateTime editDate, LocalDateTime removeDate) {
        this.id = id;
        this.domain = domain;
        this.domainId = domainId;
        this.attribute = attribute;
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

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public static AssignedAttributeBuilder builder(){
        return new AssignedAttributeBuilder();
    }
    public static class AssignedAttributeBuilder {
        private Long id;
        private String domain;
        private Long domainId;
        private Attribute attribute;
        private String value;
        private LocalDateTime createDate;
        private LocalDateTime editDate;
        private LocalDateTime removeDate;

        public AssignedAttributeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AssignedAttributeBuilder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public AssignedAttributeBuilder domainId(Long domainId) {
            this.domainId = domainId;
            return this;
        }

        public AssignedAttributeBuilder attribute(Attribute attribute) {
            this.attribute = attribute;
            return this;
        }

        public AssignedAttributeBuilder value(String value) {
            this.value = value;
            return this;
        }

        public AssignedAttributeBuilder createDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public AssignedAttributeBuilder editDate(LocalDateTime editDate) {
            this.editDate = editDate;
            return this;
        }

        public AssignedAttributeBuilder removeDate(LocalDateTime removeDate) {
            this.removeDate = removeDate;
            return this;
        }

        public AssignedAttribute build() {
            return new AssignedAttribute(id, domain, domainId, attribute, value, createDate, editDate, removeDate);
        }
    }
}
