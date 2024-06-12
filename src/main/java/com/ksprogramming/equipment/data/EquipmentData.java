package com.ksprogramming.equipment.data;

import java.time.LocalDateTime;

public class EquipmentData {
    private Long id;
    private UserData userData;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime editDate;
    private LocalDateTime removeDate;


    private EquipmentData(Long id, UserData userData, String name, LocalDateTime createDate, LocalDateTime editDate, LocalDateTime removeDate) {
        this.id = id;
        this.userData = userData;
        this.name = name;
        this.createDate = createDate;
        this.editDate = editDate;
        this.removeDate = removeDate;
    }

    public EquipmentData(UserData userData, String name, LocalDateTime createDate) {
        this.userData = userData;
        this.name = name;
        this.createDate = createDate;
    }


    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public static EquipmentDataBuilder builder() {
        return new EquipmentDataBuilder();
    }


    public static class EquipmentDataBuilder {
        private Long id;
        private UserData userData;
        private String name;
        private LocalDateTime createDate;
        private LocalDateTime editDate;
        private LocalDateTime removeDate;


        public EquipmentDataBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public EquipmentDataBuilder userData(UserData userData){
            this.userData = userData;
            return this;
        }

        public EquipmentDataBuilder createDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public EquipmentDataBuilder editDate(LocalDateTime editDate) {
            this.editDate = editDate;
            return this;
        }

        public EquipmentDataBuilder removeDate(LocalDateTime removeDate) {
            this.removeDate = removeDate;
            return this;
        }
        public EquipmentDataBuilder name(String name){
            this.name = name;
            return this;
        }

        public EquipmentData build() {
            return new EquipmentData(id, userData, name, createDate, editDate, removeDate);
        }
    }
}
