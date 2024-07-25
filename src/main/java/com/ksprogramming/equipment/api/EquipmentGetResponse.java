package com.ksprogramming.equipment.api;

import com.ksprogramming.equipment.data.EquipmentData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EquipmentGetResponse {
    private Long id;
    private UserGetResponse equipmentUserGetResponse;
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

    public EquipmentGetResponse(Long id, UserGetResponse equipmentUserGetResponse, String name, LocalDateTime createDate, LocalDateTime editDate) {
        this.id = id;
        this.equipmentUserGetResponse = equipmentUserGetResponse;
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
}
