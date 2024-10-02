package com.ksprogramming.equipment.api;

import com.ksprogramming.equipment.data.EquipmentData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EquipmentGetResponse {
    private Long id;
    private UserGetResponse equipmentUserGetResponse;
    private PictureGetResponse picture;
    private String name;
    private String createDate;
    private String editDate;
    private String removeDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public EquipmentGetResponse(EquipmentData equipmentData) {
        this.id = equipmentData.getId();
        this.createDate = equipmentData.getCreateDate().format(FORMATTER);
        this.editDate = equipmentData.getEditDate() != null? equipmentData.getEditDate().format(FORMATTER): "";
        this.removeDate = equipmentData.getRemoveDate() != null? equipmentData.getRemoveDate().format(FORMATTER): "";
        this.name = equipmentData.getName();
    }

    public EquipmentGetResponse(Long id, UserGetResponse equipmentUserGetResponse, PictureGetResponse picture,
                                String name, LocalDateTime createDate, LocalDateTime editDate, LocalDateTime removeDate) {
        this.id = id;
        this.equipmentUserGetResponse = equipmentUserGetResponse;
        this.picture = picture;
        this.name = name;
        this.createDate = createDate.format(FORMATTER);
        this.editDate = editDate != null? editDate.format(FORMATTER): "";
        this.removeDate = removeDate != null? removeDate.format(FORMATTER): "";
    }

    public EquipmentGetResponse(Long id, UserGetResponse equipmentUserGetResponse, PictureGetResponse picture, String name, LocalDateTime createDate, LocalDateTime editDate) {
        this.id = id;
        this.equipmentUserGetResponse = equipmentUserGetResponse;
        this.picture = picture;
        this.name = name;
        this.createDate = createDate.format(FORMATTER);
        this.editDate = editDate != null? editDate.format(FORMATTER) : "";
    }

    public Long getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public UserGetResponse getEquipmentUserGetResponse() {
        return equipmentUserGetResponse;
    }

    public PictureGetResponse getPicture() {
        return picture;
    }

    public static EquipmentGetResponseBuilder builder(){
        return new EquipmentGetResponseBuilder();
    }

    public static class EquipmentGetResponseBuilder {
        private Long id;
        private UserGetResponse equipmentUserGetResponse;
        private PictureGetResponse picture;
        private String name;
        private LocalDateTime createDate;
        private LocalDateTime editDate;
        private LocalDateTime removeDate;

        public EquipmentGetResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public EquipmentGetResponseBuilder equipmentUserGetResponse(UserGetResponse equipmentUserGetResponse) {
            this.equipmentUserGetResponse = equipmentUserGetResponse;
            return this;
        }

        public EquipmentGetResponseBuilder picture(PictureGetResponse picture) {
            this.picture = picture;
            return this;
        }

        public EquipmentGetResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public EquipmentGetResponseBuilder createDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public EquipmentGetResponseBuilder editDate(LocalDateTime editDate) {
            this.editDate = editDate;
            return this;
        }

        public EquipmentGetResponseBuilder removeDate(LocalDateTime removeDate) {
            this.removeDate = removeDate;
            return this;
        }

        public EquipmentGetResponse build() {
            return new EquipmentGetResponse(id, equipmentUserGetResponse, picture, name, createDate, editDate, removeDate);
        }
    }
}
