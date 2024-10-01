package com.ksprogramming.equipment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;

    public Picture() {
    }

    public Picture(Long id, String name, LocalDateTime createDate, LocalDateTime updateDate, LocalDateTime deleteDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
    }

    public Picture(String name, LocalDateTime createDate) {
        this.name = name;
        this.createDate = createDate;
    }

    public Picture(Long id, String name, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
    }

    public Picture(String name, LocalDateTime createDate, LocalDateTime updateDate) {
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Picture(Long id, String name, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public static PictureBuilder builder(){
        return new PictureBuilder();
    }

    public static class PictureBuilder {
        private Long id;
        private String name;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private LocalDateTime deleteDate;

        public PictureBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PictureBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PictureBuilder createDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public PictureBuilder updateDate(LocalDateTime updateDate) {
            this.updateDate = updateDate;
            return this;
        }

        public PictureBuilder deleteDate(LocalDateTime deleteDate) {
            this.deleteDate = deleteDate;
            return this;
        }

        public Picture build() {
            return new Picture(id, name, createDate, updateDate, deleteDate);
        }
    }
}
