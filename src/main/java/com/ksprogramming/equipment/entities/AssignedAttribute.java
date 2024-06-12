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

}
