package com.ksprogramming.equipment.repository;

import com.ksprogramming.equipment.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
    @Query("from Attribute a where a.domain = 'Equipment' and a.removeDate is null")
    public List<Attribute> getAttributeByDomain();

    @Query("from Attribute a where a.removeDate is null")
    public List<Attribute> findAllAttributes();
    @Query("from Attribute a join a.assignedAttributes where a.id = :id")
    public List<Attribute> findAttributeWithValueById(Long id);

}
