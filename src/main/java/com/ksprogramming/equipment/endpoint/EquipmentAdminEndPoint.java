package com.ksprogramming.equipment.endpoint;

import com.ksprogramming.equipment.api.*;
import com.ksprogramming.equipment.data.*;
import com.ksprogramming.equipment.enumes.Authority;
import com.ksprogramming.equipment.enumes.AttributeType;
import com.ksprogramming.equipment.enumes.DictionaryType;
import com.ksprogramming.equipment.enumes.Language;
import com.ksprogramming.equipment.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin/api/crs")
public class EquipmentAdminEndPoint {
    private EquipmentServiceInterface equipmentService;
    private AssignedAttributeService assignedAttributeService;
    private AttributeServiceInterface attributeService;
    private UserServiceInterface userService;
    private UserAuthorityServiceInterface userAuthorityService;
    private DictionariesService dictionariesService;

    public EquipmentAdminEndPoint(EquipmentServiceInterface equipmentService, AssignedAttributeService assignedAttributeService,
                                  AttributeServiceInterface attributeService, UserServiceInterface userService,
                                  UserAuthorityServiceInterface userAuthorityService, DictionariesService dictionariesService) {
        this.equipmentService = equipmentService;
        this.assignedAttributeService = assignedAttributeService;
        this.attributeService = attributeService;
        this.userService = userService;
        this.userAuthorityService = userAuthorityService;
        this.dictionariesService = dictionariesService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<EquipmentUserGetResponse>> findAllEquipmentUsers() {
        List<EquipmentUserGetResponse> equipmentUsersDataToResponse = equipmentUsersDataToResponse(userService.findAll());
        if (equipmentUsersDataToResponse.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(equipmentUsersDataToResponse);
        }
    }

    @PostMapping("/user")
    public void createUser(@RequestBody EquipmentUserPostRequest request) {
        userService.registerUser(new UserData(
                request.getLogin(),
                request.getPasswordHash(),
                false,
                request.getLanguage(),
                userAuthoritiesArrayToList(request.getAuthorities()),
                LocalDateTime.now()));
    }
    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody EquipmentUserPutRequest equipmentUserPutRequest) {
        userService.update(equipmentUserPutRequestToData(id, equipmentUserPutRequest));
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
    @PutMapping("/user-change-password/{id}")
    public void changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request) {
        userService.changePasswordAdmin(id, request.getNewPasswordHash());
    }
    @GetMapping("/authority/{id}")
    public ResponseEntity<List<UserAuthorityGetResponse>> findAuthorityById(@PathVariable Long id) {
        List<UserAuthorityGetResponse> userAuthorities = userAuthoritiesDataToGetResponse(userAuthorityService.findById(id));
        if (userAuthorities.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(userAuthorities);
        }
    }
    @GetMapping("/attribute/{id}")
    public ResponseEntity<AttributeWithDetailsGetResponse> getAttribute(@PathVariable Long id) {
        AttributeWithDetailsGetResponse attribute = new AttributeWithDetailsGetResponse(attributeDataToResponse(attributeService.getAttribute(id)),
                assignedAttributeDataToResponse(assignedAttributeService.getAttributeWithValues(id)));
        if (attribute == null){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(attribute);
        }
    }
    @GetMapping("/attributes")
    public ResponseEntity<List<AttributeGetResponse>> findAttributes() {
        List<AttributeGetResponse> attributes = attributesDataToResponse(attributeService.findAttributes());
        if (attributes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(attributes);
        }
    }
    @GetMapping("/attribute-types")
    public ResponseEntity<AttributeType[]> getAttributeTypes() {
        AttributeType[] attributeTypes = AttributeType.values();
        if (attributeTypes == null){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(attributeTypes);
        }
    }
    @GetMapping("/attribute/value/{id}")
    public ResponseEntity<List<AssignedAttributeGetResponse>> attributeWithValues(@PathVariable Long id) {
        List<AssignedAttributeGetResponse> assignedAttributes = assignedAttributeDataToResponse(attributeService.findAttributesWithValue(id));
        if (assignedAttributes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(assignedAttributes);
        }
    }
    @PutMapping("/attribute/{id}")
    public void updateAttribute(@PathVariable Long id, @RequestBody AttributeGetRequest attributeGetRequest) {
        AttributeData attributeData = new AttributeData(id, attributeGetRequest.getName(), attributeGetRequest.getType(), attributeGetRequest.getDomain());
        attributeService.update(attributeData);
    }
    @PostMapping("/attribute")
    public void createAttribute(@RequestBody AttributePostRequest attributePostRequest) {
        attributeService.create(attributeRequestToData(attributePostRequest));
    }
    @DeleteMapping("/attribute/{id}")
    public void deleteAttribute(@PathVariable Long id) {
        attributeService.delete(id);
    }
    @PostMapping("/equipment")
    public void createEquipment(@RequestBody EquipmentPostRequest equipmentPostRequest) {
        EquipmentData equipment = new EquipmentData(userService.getLoggedUser(), equipmentPostRequest.getName(), LocalDateTime.now());
        equipmentService.create(equipment, valuesPostRequestToData(equipmentPostRequest.getValues()));
    }
    @GetMapping("/equipments")
    public ResponseEntity<List<EquipmentGetResponse>> findAll() {
        List<EquipmentGetResponse> equipments = equipmentsDataToEquipmentsGetResponse();
        if (equipments.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(equipments);
        }
    }
    @GetMapping("/equipments/attributes")
    public ResponseEntity<List<AttributeGetResponse>>findAllAttributes() {
        List<AttributeGetResponse> attributes = attributesDataToResponse(attributeService.findAttributesByDomain());
        if (attributes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(attributes);
        }
    }
    @GetMapping("/equipment/{id}")
    public ResponseEntity<EquipmentWithAttributesGetResponse> get(@PathVariable Long id) {
        EquipmentsWithDetailsData equipmentsWithDetails = equipmentService.get(id);
        EquipmentWithAttributesGetResponse equipmentWithAttributesGetResponse = prepareEquipmentWithAttributesGetResponse(
                equipmentsWithDetails.getEquipment(), equipmentsWithDetails.getAttributes(), attributeService.findAttributesByDomain());
        if (equipmentWithAttributesGetResponse == null){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(equipmentWithAttributesGetResponse);
        }
    }
    @DeleteMapping("/equipment/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.remove(id);
    }
    @PutMapping("/equipment/{id}")
    public void updateEquipment(@PathVariable Long id, @RequestBody EquipmentPutRequest request) {
        EquipmentData equipment = new EquipmentData(id, userService.getLoggedUser(), request.getName(), LocalDateTime.now());
        equipmentService.update(equipment, valuesPutRequestToData(request.getValues()));
    }
    @GetMapping("/dictionaries")
    public List<DictionaryData> getAttributesTypeDictionary() {
        return dictionariesService.getDictionary(DictionaryType.ATTRIBUTE_TYPES, Language.PL);
    }
    private List<ValueData> valuesPostRequestToData(List<ValuePostRequest> values) {
        List<ValueData> list = new ArrayList<>();
        values.forEach(value -> {
            list.add(new ValueData(value.getId(), value.getValue(), value.getAttributeName()));
        });
        return list;
    }
    private UserData equipmentUserPutRequestToData(Long id, EquipmentUserPutRequest equipmentUserPutRequest) {
        UserData userData = new UserData(id, equipmentUserPutRequest.getLogin(),
                equipmentUserPutRequest.getEmailConfirmed(), equipmentUserPutRequest.getLanguage(),
                userAuthoritiesArrayToList(equipmentUserPutRequest.getAuthorities()));
        return userData;
    }
    private List<UserAuthorityData> userAuthoritiesArrayToList(String[] authorities) {
        List<UserAuthorityData> list = new ArrayList<>();
        for(String authority : authorities){
            list.add(new UserAuthorityData(Authority.valueOf(authority).getCodeWithRole()));
        }
        return list;
    }
    private List<UserAuthorityGetResponse> userAuthoritiesDataToGetResponse(List<UserAuthorityData> userAuthorityDataList) {
        List<UserAuthorityGetResponse> userAuthorityGetResponseList = new ArrayList<>();
        userAuthorityDataList.stream()
                .forEach(authority -> {
                    userAuthorityGetResponseList.add(new UserAuthorityGetResponse(authority.getId(),
                            equipmentUserDataToGetResponse(authority.getUserData()),
                            Authority.findByCodeWithRole(authority.getAuthority()).toString()));
                });

        return userAuthorityGetResponseList;
    }
    private EquipmentUserGetResponse equipmentUserDataToGetResponse(UserData equipmentUserData) {
        return new EquipmentUserGetResponse(equipmentUserData.getId(), equipmentUserData.getLogin(), equipmentUserData.getPasswordHash(),
                equipmentUserData.getEmailConfirmed(), equipmentUserData.getLanguage(), equipmentUserData.getRegistrationDate());
    }
    private List<EquipmentUserGetResponse> equipmentUsersDataToResponse(List<UserData> equipmentUserData) {
        List<EquipmentUserGetResponse> list = new ArrayList<>();
        equipmentUserData.stream()
                .forEach(user -> {
                    list.add(new EquipmentUserGetResponse(user.getId(), user.getLogin(), user.getPasswordHash(),
                            user.getEmailConfirmed(), user.getLanguage(), user.getRegistrationDate()));
                });
        return list;
    }
    private AttributeData attributeRequestToData(AttributePostRequest attributePostRequest) {
        return new AttributeData(attributePostRequest.getName(), attributePostRequest.getType(), attributePostRequest.getDomain(), LocalDateTime.now());
    }
    private List<AttributeGetResponse> attributesDataToResponse(List<AttributeData> attributesData) {
        List<AttributeGetResponse> attributes = new ArrayList<>();
        attributesData.stream()
                .forEach(attribute -> {
                    attributes.add(new AttributeGetResponse(attribute));
                });
        return attributes;
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
        equipmentService.findAll().stream()
                .forEach(equipment -> equipments.add(new EquipmentGetResponse(equipment.getId()
                        , equipmentUserDataToGetResponse(equipment.getUserData())
                        , equipment.getName()
                        , equipment.getCreateDate()
                        , equipment.getEditDate())));
        return equipments;
    }
    private List<AssignedAttributeGetResponse> assignedAttributeDataToResponse(List<AssignedAttributeData> assignedAttributesData) {
        List<AssignedAttributeGetResponse> list = new ArrayList<>();
        assignedAttributesData.stream()
                .forEach(assignedAttributeData -> {
                    list.add(new AssignedAttributeGetResponse(assignedAttributeData));
                });
        return list;
    }
    private AttributeGetResponse attributeDataToResponse(AttributeData attribute) {
        return new AttributeGetResponse(attribute);
    }
    private List<ValueData> valuesPutRequestToData(List<ValuePutRequest> values) {
        List<ValueData> list = new ArrayList<>();
        values.forEach(value -> {
            list.add(new ValueData(value.getId(), value.getValue(), value.getAttributeName()));
        });
        return list;
    }
}
