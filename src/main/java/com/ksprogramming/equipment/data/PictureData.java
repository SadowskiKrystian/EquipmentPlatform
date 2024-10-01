package com.ksprogramming.equipment.data;

import java.time.LocalDateTime;

public class PictureData {
    private Long id;
    private String path;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;

    public PictureData() {
    }

    public PictureData(Long id, String path, LocalDateTime createDate, LocalDateTime updateDate, LocalDateTime deleteDate) {
        this.id = id;
        this.path = path;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
    }

    public PictureData(Long id, String path, LocalDateTime createDate) {
        this.id = id;
        this.path = path;
        this.createDate = createDate;
    }

    public PictureData(String path) {
        this.path = path;
    }

    public PictureData(String path, LocalDateTime createDate) {
        this.path = path;
        this.createDate = createDate;
    }

    public PictureData(Long id, String path, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.path = path;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public void setDeleteDate(LocalDateTime deleteDate) {
        this.deleteDate = deleteDate;
    }
}
