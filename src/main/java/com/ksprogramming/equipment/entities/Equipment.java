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

    public Equipment(Long id, User user, String name,
                           LocalDateTime createDate, LocalDateTime editDate, LocalDateTime removeDate) {
        this.id = id;
        this.user = user;
        this.name = name;
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
}
