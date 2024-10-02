package com.ksprogramming.equipment.api;

import java.time.LocalDateTime;

public class PictureGetResponse {
    private Long id;
    private String path;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;

    public PictureGetResponse() {
    }

    public PictureGetResponse(Long id, String path, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.path = path;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public PictureGetResponse(Long id, String path, LocalDateTime createDate, LocalDateTime updateDate, LocalDateTime deleteDate) {
        this.id = id;
        this.path = path;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
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

    public static PictureGetResponseBuilder builder(){
        return new PictureGetResponseBuilder();
    }

    public static class PictureGetResponseBuilder {
        private Long id;
        private String path;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private LocalDateTime deleteDate;

        public PictureGetResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PictureGetResponseBuilder path(String path) {
            this.path = path;
            return this;
        }

        public PictureGetResponseBuilder createDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public PictureGetResponseBuilder updateDate(LocalDateTime updateDate) {
            this.updateDate = updateDate;
            return this;
        }

        public PictureGetResponseBuilder deleteDate(LocalDateTime deleteDate) {
            this.deleteDate = deleteDate;
            return this;
        }

        public PictureGetResponse build() {
            return new PictureGetResponse(id, path, createDate, updateDate, deleteDate);
        }
    }
}
