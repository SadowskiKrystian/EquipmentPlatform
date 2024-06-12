package com.ksprogramming.equipment.entities;

import com.ksprogramming.equipment.data.AttributeData;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String domain;
    @OneToMany(mappedBy = "attribute")
    private List<AssignedAttribute> assignedAttributes;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "edit_date")
    private LocalDateTime editDate;
    @Column(name = "remove_date")
    private LocalDateTime removeDate;

    public Attribute() {
    }

    public Attribute(Long id) {
        this.id = id;
    }
    public Attribute(AttributeData attributeData){
        this.id = attributeData.getId();
        this.name = attributeData.getName();
        this.type = attributeData.getType();
        this.domain = attributeData.getDomain();
        this.createDate = attributeData.getCreateDate();
        this.editDate = attributeData.getEditDate();
        this.removeDate = attributeData.getRemoveDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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

    public List<AssignedAttribute> getAssignedAttributesData() {
        return assignedAttributes;
    }

    public void setAssignedAttributes(List<AssignedAttribute> assignedAttributes) {
        this.assignedAttributes = assignedAttributes;
    }
}
