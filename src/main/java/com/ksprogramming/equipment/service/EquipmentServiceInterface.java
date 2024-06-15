package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.EquipmentData;
import com.ksprogramming.equipment.data.EquipmentsWithDetailsData;
import com.ksprogramming.equipment.data.ValueData;

import java.util.List;

public interface EquipmentServiceInterface {
    EquipmentData create(EquipmentData equipmentData, List<ValueData> values);
    void update(EquipmentData equipmentData, List<ValueData> values);
    EquipmentsWithDetailsData get(Long id);
    List<EquipmentData> findAll();
    List<EquipmentData> findByLogin(String login);
    void remove(Long id);

}
