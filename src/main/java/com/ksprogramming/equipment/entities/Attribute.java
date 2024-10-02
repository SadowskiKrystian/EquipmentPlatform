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

    public Attribute(Long id, String name, String type, String domain,
                     List<AssignedAttribute> assignedAttributes, LocalDateTime createDate, LocalDateTime editDate, LocalDateTime removeDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.domain = domain;
        this.assignedAttributes = assignedAttributes;
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

    public List<AssignedAttribute> getAssignedAttributes() {
        return assignedAttributes;
    }

    public void setAssignedAttributes(List<AssignedAttribute> assignedAttributes) {
        this.assignedAttributes = assignedAttributes;
    }

    public static AttributeBuilder builder(){
        return new AttributeBuilder();
    }

    public static class AttributeBuilder{
        private Long id;
        private String name;
        private String type;
        private String domain;
        private List<AssignedAttribute> assignedAttributes;
        private LocalDateTime createDate;
        private LocalDateTime editDate;
        private LocalDateTime removeDate;

        public AttributeBuilder id(Long id){
            this.id = id;
            return this;
        }

        public AttributeBuilder name(String name){
            this.name = name;
            return this;
        }

        public AttributeBuilder type(String type){
            this.type = type;
            return this;
        }

        public AttributeBuilder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public AttributeBuilder assignedAttributes(List<AssignedAttribute> assignedAttributes){
            this.assignedAttributes = assignedAttributes;
            return this;
        }

        public AttributeBuilder createDate(LocalDateTime createDate){
            this.createDate = createDate;
            return this;
        }

        public AttributeBuilder editDate(LocalDateTime editDate){
            this.editDate = editDate;
            return this;
        }

        public AttributeBuilder removeDate(LocalDateTime removeDate){
            this.removeDate = removeDate;
            return this;
        }

        public Attribute build(){
            return new Attribute(id, name, type, domain, assignedAttributes, createDate, editDate, removeDate);
        }
    }
}
