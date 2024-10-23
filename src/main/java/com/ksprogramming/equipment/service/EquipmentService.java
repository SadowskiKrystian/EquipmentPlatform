package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.*;
import com.ksprogramming.equipment.entities.*;
import com.ksprogramming.equipment.enumes.DomainType;
import com.ksprogramming.equipment.mapper.AssignedAttributeMapper;
import com.ksprogramming.equipment.mapper.AttributeMapper;
import com.ksprogramming.equipment.mapper.AttributeWithAssignedAttributeMapper;
import com.ksprogramming.equipment.mapper.EquipmentMapper;
import com.ksprogramming.equipment.repository.AssignedAttributeRepository;
import com.ksprogramming.equipment.repository.AttributeRepository;
import com.ksprogramming.equipment.repository.EquipmentRepository;
import com.ksprogramming.equipment.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EquipmentService implements EquipmentServiceInterface {
    private EquipmentRepository equipmentRepository;
    private AttributeRepository attributeRepository;
    private AssignedAttributeRepository assignedAttributeRepository;
    private UserRepository userRepository;
    private AssignedAttributeServiceInterface assignedAttributeService;
    private UserServiceInterface equipmentUserService;


    public EquipmentService(EquipmentRepository equipmentRepository, AssignedAttributeRepository assignedAttributeRepository,
                            UserRepository userRepository, AssignedAttributeServiceInterface assignedAttributeService,
                            UserServiceInterface userService) {
        this.equipmentRepository = equipmentRepository;
        this.assignedAttributeRepository = assignedAttributeRepository;
        this.userRepository = userRepository;
        this.assignedAttributeService = assignedAttributeService;
        this.equipmentUserService = userService;
    }

    public EquipmentData create(EquipmentData equipmentData, List<ValueData> values) {
        validate(equipmentData, values);
        EquipmentData save = EquipmentMapper.entityToData(equipmentRepository.save(EquipmentMapper.dataToEntity(equipmentData)));
        values.forEach(value -> {
            assignedAttributeRepository.save(new AssignedAttribute(DomainType.EQUIPMENT.toString(), save.getId(), new Attribute(value.getId()), value.getValuee(), LocalDateTime.now()));
        });
        return save;
    }

    public void update(EquipmentData equipmentData, List<ValueData> values) {
        EquipmentData equipment = EquipmentMapper.entityToData(equipmentRepository.getReferenceById(equipmentData.getId().intValue()));
        equipment.setPicture(equipmentData.getPicture());
        equipment.setName(equipmentData.getName());
        equipment.setEditDate(LocalDateTime.now());
        validate(equipment, values);
        equipmentRepository.save(EquipmentMapper.dataToEntity(equipment));
        updateValues(equipment.getId(), values);
    }

    public EquipmentsWithDetailsData get(Long id) {
        Equipment equipment = equipmentRepository.getReferenceById(id.intValue());
        List<AssignedAttribute> assignedAttributes = assignedAttributeRepository.getEquipmentWithAttribute(id);
        List<AttributeData> attributes = new ArrayList<>();
        assignedAttributes.stream().forEach(attribute -> attributes.add(AttributeWithAssignedAttributeMapper.entityToData(attribute.getAttribute(), attribute)));
        return new EquipmentsWithDetailsData(EquipmentMapper.entityToData(equipment), attributes);

    }

    public List<EquipmentData> findAll() {
        List<EquipmentData> equipments = new ArrayList<>();
        equipmentRepository.findAll().stream().forEach(equipmentEntity -> equipments.add(EquipmentMapper.entityToData(equipmentEntity)));
        return equipments;
    }

    public List<EquipmentData> findByLogin(String name) {
        List<EquipmentData> equipments = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        equipmentRepository.findByLogin(authentication.getName(), name).stream()
                .forEach(equipmentEntity -> equipments.add(EquipmentMapper.entityToData(equipmentEntity)));
        return equipments;
    }

    public void remove(Long id) {
        EquipmentData equipmentData = EquipmentMapper.entityToData(equipmentRepository.getReferenceById(id.intValue()));
        equipmentData.setRemoveDate(LocalDateTime.now());
        equipmentRepository.save(EquipmentMapper.dataToEntity(equipmentData));
        List<AssignedAttributeData> assignedAttributesData = AssignedAttributeMapper.entitToDataList(assignedAttributeRepository.getEquipmentWithAttribute(id));
        assignedAttributesData.forEach(assignedAttributeData -> {
            assignedAttributeData.setRemoveDate(LocalDateTime.now());
            assignedAttributeService.save(assignedAttributeData);

        });
        EquipmentData equipment = EquipmentMapper.entityToData(equipmentRepository.getReferenceById(id.intValue()));
        equipment.setRemoveDate(equipmentData.getRemoveDate());
        equipmentRepository.save(EquipmentMapper.dataToEntity(equipment));

    }

    private void validate(EquipmentData equipmentData, List<ValueData> values) {
        if (equipmentData.getName().length() > 199) {
            throw new IllegalArgumentException("Name to Long. Maximum lenght 200 characters");
        }
        values.forEach(value -> {
            if (value.getValuee().length() > 199) {
                throw new IllegalArgumentException(value.getAttributeName() + " value too long. Maximum lenght 200 characters");
            }
        });
    }

  private void updateValues(Long equipmentId, List<ValueData> values) {
        List<AssignedAttributeData> assignedAttributes = AssignedAttributeMapper.entitToDataList(assignedAttributeRepository.getEquipmentWithAttribute(equipmentId));
        if (assignedAttributes.size() > 0) {
            Map<Long, AssignedAttributeData> assignedAttributeList = new HashMap<>();
            assignedAttributes.stream().forEach(assignedAttribute -> {
                assignedAttributeList.put(assignedAttribute.getId(), new AssignedAttributeData(assignedAttribute.getId(), assignedAttribute.getDomain(),
                        assignedAttribute.getDomainId(), assignedAttribute.getAttribute(), assignedAttribute.getValue(), assignedAttribute.getCreateDate(),
                        assignedAttribute.getEditDate(), assignedAttribute.getRemoveDate()));
            });
            values.forEach(value -> {
                if (assignedAttributeList.containsKey(value.getAssignedAttributeId())) {
                    assignedAttributeList.get(value.getAssignedAttributeId()).setValue(value.getValuee());
                } else {
                    assignedAttributeRepository.save(new AssignedAttribute(DomainType.EQUIPMENT.toString(), equipmentId, new Attribute(value.getId()), value.getValuee(),
                            LocalDateTime.now()));
                }
            });
            for (Map.Entry<Long, AssignedAttributeData> map : assignedAttributeList.entrySet()) {
                assignedAttributeRepository.save(new AssignedAttribute(map.getKey(), map.getValue().getDomain(), map.getValue().getDomainId(),
                        AttributeMapper.dataToEntity(map.getValue().getAttribute()), map.getValue().getValue(), map.getValue().getCreateDate(), map.getValue().getEditDate(),
                        map.getValue().getRemoveDate()));
            }
        }
    }
}
