package com.ksprogramming.equipment.endpoint;

import com.ksprogramming.equipment.api.*;
import com.ksprogramming.equipment.data.*;
import com.ksprogramming.equipment.enumes.Authority;
import com.ksprogramming.equipment.service.AssignedAttributeServiceInterface;
import com.ksprogramming.equipment.service.AttributeServiceInterface;
import com.ksprogramming.equipment.service.EquipmentServiceInterface;
import com.ksprogramming.equipment.service.UserServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/crs")
public class EquipmentEndPoint {
    private UserServiceInterface userService;
    private EquipmentServiceInterface equipmentService;
    private AttributeServiceInterface attributeService;
    private AssignedAttributeServiceInterface assignedAttributeService;

    public EquipmentEndPoint(UserServiceInterface userService, EquipmentServiceInterface equipmentService,
                             AttributeServiceInterface attributeService, AssignedAttributeServiceInterface assignedAttributeService) {
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.attributeService = attributeService;
        this.assignedAttributeService = assignedAttributeService;
    }
    @PostMapping("/register-user")
    public void register(@RequestBody UserPostRequest request, HttpServletRequest httpServletRequest) {
        List<UserAuthorityData> authorities = new ArrayList<>();
        authorities.add(new UserAuthorityData(Authority.USER.getCode()));
        UserData userCreateRequest = new UserData(request.getLogin(), request.getPasswordHash(), false, request.getLanguage(),
                authorities, LocalDateTime.now());
        userService.registerUser(userCreateRequest);
    }

    @GetMapping("equipment/{id}")
    public EquipmentWithAttributesGetResponse getEquipment(@PathVariable Long id){
        EquipmentsWithDetailsData equipment = equipmentService.get(id);
        return prepareEquipmentWithAttributesGetResponse(
                equipment.getEquipment(), equipment.getAttributes(), attributeService.findAttributesByDomain());
    }
    @DeleteMapping("/equipment/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.remove(id);

    }
    @PutMapping("/equipment/{id}")
    public void updateEquipment(@PathVariable Long id, @RequestBody EquipmentPutRequest request){
        EquipmentData equipment = new EquipmentData(userService.getLoggedUser(), request.getName(), LocalDateTime.now());
        equipmentService.update(equipment, valuesPutRequestToData(request.getValues()));
    }
    @PostMapping("/equipment")
    public void createEquipment(@RequestBody EquipmentPostRequest equipmentPostRequest) {
        EquipmentData equipment = new EquipmentData(userService.getLoggedUser(), equipmentPostRequest.getName(), LocalDateTime.now());
        equipmentService.create(equipment, valuesPostRequestToData(equipmentPostRequest.getValues()));
    }
    @GetMapping("/equipments")
    public List<EquipmentGetResponse> findAll() {
        return equipmentsDataToEquipmentsGetResponse();
    }
    @GetMapping("/equipments/attributes")
    public List<AttributeGetResponse> findAllAttributes() {
        return attributesDataToResponse(attributeService.findAttributesByDomain());
    }
    private List<ValueData> valuesPostRequestToData(List<ValuePostRequest> values) {
        List<ValueData> list = new ArrayList<>();
        values.forEach(value -> {
            list.add(new ValueData(value.getId(), value.getValue(), value.getAttributeName()));
        });
        return list;
    }
    private List<ValueData> valuesPutRequestToData(List<ValuePutRequest> values) {
        List<ValueData> list = new ArrayList<>();
        values.forEach(value -> {
            list.add(new ValueData(value.getId(), value.getValue(), value.getAttributeName()));
        });
        return list;
    }
    private static EquipmentWithAttributesGetResponse prepareEquipmentWithAttributesGetResponse(EquipmentData equipmentData, List<AttributeData> assignedAttributesData, List<AttributeData> attributesData) {
        List<AttributeGetResponse> assignedAttributes = new ArrayList<>();
        assignedAttributesData.stream()
                .forEach(attribute -> assignedAttributes.add(new AttributeGetResponse(attribute)));
        List<AttributeGetResponse> attributes = new ArrayList<>();
        attributesData.stream()
                .forEach(attribute -> {
                    attributes.add(new AttributeGetResponse(attribute));
                });
        return new EquipmentWithAttributesGetResponse(new EquipmentGetResponse(equipmentData),
                assignedAttributes, attributes);
    }
    private List<EquipmentGetResponse> equipmentsDataToEquipmentsGetResponse() {
        List<EquipmentGetResponse> equipments = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        equipmentService.findByLogin(authentication.getName()).stream()
                .forEach(equipment -> equipments.add(new EquipmentGetResponse(equipment.getId()
                        , equipmentUserDataToGetResponse(equipment.getUserData())
                        , equipment.getName()
                        , equipment.getCreateDate()
                        , equipment.getEditDate())));
        return equipments;
    }
    private List<AttributeGetResponse> attributesDataToResponse(List<AttributeData> attributesData) {
        List<AttributeGetResponse> attributes = new ArrayList<>();
        attributesData.stream()
                .forEach(attribute -> {
                    attributes.add(new AttributeGetResponse(attribute));
                });
        return attributes;
    }
    private EquipmentUserGetResponse equipmentUserDataToGetResponse(UserData userData) {
        return new EquipmentUserGetResponse(userData.getId(), userData.getLogin(), userData.getPasswordHash(),
                userData.getEmailConfirmed(), userData.getLanguage(), userData.getRegistrationDate());
    }
}
