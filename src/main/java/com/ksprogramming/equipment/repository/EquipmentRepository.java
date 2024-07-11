package com.ksprogramming.equipment.repository;

import com.ksprogramming.equipment.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    @Override
    @Query("FROM Equipment WHERE removeDate is null")
    List<Equipment> findAll();
    @Query("from Equipment e where e.user.login = :login and (:name is null or e.name like %:name%) and e.removeDate is null ")
    List<Equipment> findByLogin(String login, String name);

}
