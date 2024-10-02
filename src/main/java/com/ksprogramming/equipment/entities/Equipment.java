package com.ksprogramming.equipment.entities;

import com.ksprogramming.equipment.data.EquipmentData;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    private Picture picture;
    private String name;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "edit_date")
    private LocalDateTime editDate;
    @Column(name = "remove_date")
    private LocalDateTime removeDate;

    public Equipment() {
    }

    public Equipment(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Equipment(String name, LocalDateTime createDate) {
        this.name = name;
        this.createDate = createDate;
    }


    public Equipment(EquipmentData equipmentData){
        this.id = equipmentData.getId();
        this.name = equipmentData.getName();
        this.createDate = equipmentData.getCreateDate();
        this.editDate = equipmentData.getEditDate();
        this.removeDate = equipmentData.getRemoveDate();
    }

    public Equipment(Long id, User user, Picture picture, String name,
                           LocalDateTime createDate, LocalDateTime editDate, LocalDateTime removeDate) {
        this.id = id;
        this.user = user;
        this.picture = picture;
        this.name = name;
        this.createDate = createDate;
        this.editDate = editDate;
        this.removeDate = removeDate;
    }

    public Equipment(User user, Picture picture, String name, LocalDateTime createDate) {
        this.user = user;
        this.picture = picture;
        this.name = name;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public static EquipmentBuilder builder(){
        return new EquipmentBuilder();
    }

    public static class EquipmentBuilder{
        private Long id;
        private User user;
        private Picture picture;
        private String name;
        private LocalDateTime createDate;
        private LocalDateTime editDate;
        private LocalDateTime removeDate;

        public EquipmentBuilder id(Long id){
            this.id = id;
            return this;
        }

        public EquipmentBuilder user(User user){
            this.user = user;
            return this;
        }

        public EquipmentBuilder picture(Picture picture){
            this.picture = picture;
            return this;
        }

        public EquipmentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public EquipmentBuilder createDate(LocalDateTime createDate){
            this.createDate = createDate;
            return this;
        }

        public EquipmentBuilder editDate(LocalDateTime editDate){
            this.editDate = editDate;
            return this;
        }

        public EquipmentBuilder removeDate(LocalDateTime removeDate){
            this.removeDate = removeDate;
            return this;
        }

        public Equipment build(){
            return new Equipment(id, user, picture, name, createDate, editDate, removeDate);
        }
    }

}
