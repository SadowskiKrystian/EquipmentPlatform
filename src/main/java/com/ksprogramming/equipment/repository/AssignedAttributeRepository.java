package com.ksprogramming.equipment.repository;

import com.ksprogramming.equipment.entities.AssignedAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignedAttributeRepository extends JpaRepository<AssignedAttribute, Integer> {
    @Query("from AssignedAttribute a join a.attribute WHERE a.domainId = :id and a.attribute.removeDate is null")
    public List<AssignedAttribute> getEquipmentWithAttribute(@Param("id") Long id);
    @Query("from AssignedAttribute a join a.attribute WHERE a.attribute.id = :id")
    public List<AssignedAttribute> getAttributeWithValue(@Param("id") Long id);

}
