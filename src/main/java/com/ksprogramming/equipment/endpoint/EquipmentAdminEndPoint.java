package com.ksprogramming.equipment.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksprogramming.equipment.api.*;
import com.ksprogramming.equipment.data.*;
import com.ksprogramming.equipment.enumes.Authority;
import com.ksprogramming.equipment.enumes.AttributeType;
import com.ksprogramming.equipment.enumes.DictionaryType;
import com.ksprogramming.equipment.enumes.Language;
import com.ksprogramming.equipment.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
    private EmailServiceInterface emailService;
    private TokenServiceInterface tokenService;
    private NotificationServiceInterface notificationService;
    private FileStorageServiceInterface fileStorageService;
    private PictureServiceInterface pictureService;

    public EquipmentAdminEndPoint(EquipmentServiceInterface equipmentService, AssignedAttributeService assignedAttributeService,
                                  AttributeServiceInterface attributeService, UserServiceInterface userService,
                                  UserAuthorityServiceInterface userAuthorityService, DictionariesService dictionariesService, EmailServiceInterface emailService, TokenServiceInterface tokenService, NotificationServiceInterface notificationService, FileStorageServiceInterface fileStorageService, PictureServiceInterface pictureService) {
        this.equipmentService = equipmentService;
        this.assignedAttributeService = assignedAttributeService;
        this.attributeService = attributeService;
        this.userService = userService;
        this.userAuthorityService = userAuthorityService;
        this.dictionariesService = dictionariesService;
        this.emailService = emailService;
        this.tokenService = tokenService;
        this.notificationService = notificationService;
        this.fileStorageService = fileStorageService;
        this.pictureService = pictureService;
    }
    @PostMapping("/notification")
    public void createNotification(@RequestBody CreateNotificationPostRequest request) {
        NotificationData notificationData = new NotificationData();
        notificationData.setTitle(request.getTitle());
        notificationData.setContent(request.getContent());
        request.getUsers().forEach(user -> {
            UserData userById = userService.getUserById(user);
            notificationData.setReceiverId(userById);
            notificationService.createNotification(notificationData);
        });
    }
    @GetMapping("/notifications")
    public List<NotificationGetResponse> findAllNotifications() {
        return notificationsDataToRespone(notificationService.findAllNotifications());
    }
    @DeleteMapping("/notification/{id}")
    public void deleteNotification(@PathVariable("id") Long id) {
        notificationService.deleteNotification(id);
    }

    private List<NotificationGetResponse> notificationsDataToRespone(List<NotificationData> notifications) {
        List<NotificationGetResponse> responses = new ArrayList<>();
        notifications.forEach(notification -> {
            responses.add(new NotificationGetResponse(notification.getId(), notification.getSenderLogin(), userDataToGetResponse(notification.getReceiverId()),
                    notification.getTitle(), notification.getContent(), notification.getCreateDateTime(), notification.getSeenDateTime()))
            ;
        });
        return responses;
    }

    @GetMapping("/users")
    public List<UserGetResponse> findAllEquipmentUsers() {
        return usersDataToResponse(userService.findAll());
    }

    @PostMapping("/user")
    public void createUser(@RequestBody UserPostRequest request) {
        userService.registerUser(new UserData(
                request.getLogin(),
                request.getPasswordHash(),
                false,
                request.getLanguage(),
                userAuthoritiesArrayToList(request.getAuthorities()),
                LocalDateTime.now()));
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserPutRequest equipmentUserPutRequest) {
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
    public List<UserAuthorityGetResponse> findAuthorityById(@PathVariable Long id) {
        return userAuthoritiesDataToGetResponse(userAuthorityService.findById(id));
    }

    @GetMapping("/attribute/{id}")
    public AttributeWithDetailsGetResponse getAttribute(@PathVariable Long id) {
        return new AttributeWithDetailsGetResponse(attributeDataToResponse(attributeService.getAttribute(id)),
                assignedAttributeDataToResponse(assignedAttributeService.getAttributeWithValues(id)));
    }

    @GetMapping("/attributes")
    public List<AttributeGetResponse> findAttributes() {
        return attributesDataToResponse(attributeService.findAttributes());
    }

    @GetMapping("/attribute-types")
    public AttributeType[] getAttributeTypes() {
        return AttributeType.values();
    }

    @GetMapping("/attribute/value/{id}")
    public List<AssignedAttributeGetResponse> attributeWithValues(@PathVariable Long id) {
        return assignedAttributeDataToResponse(attributeService.findAttributesWithValue(id));
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
    public void createEquipment(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("json") String json) {
        String fileName;
        PictureData picture = null;
        EquipmentPostRequest equipmentPostRequest;
        if (file != null){
            fileName = fileStorageService.saveImageOnServer(file);
            picture = pictureService.createPicture(new PictureData(fileName));
        }
        try{
            ObjectMapper mapper = new ObjectMapper();
            equipmentPostRequest = mapper.readValue(json, EquipmentPostRequest.class);
            System.out.println(equipmentPostRequest.getName());
            equipmentPostRequest.getValues().forEach(value -> System.out.println(value.getValue()));
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EquipmentData equipment = new EquipmentData(userService.getLoggedUser(), picture, equipmentPostRequest.getName(), LocalDateTime.now());
        equipmentService.create(equipment, valuesPostRequestToData(equipmentPostRequest.getValues()));
    }

    @GetMapping("/equipments")
    public List<EquipmentGetResponse> findAll() {
        return equipmentsDataToEquipmentsGetResponse();
    }

    @GetMapping("/equipments/attributes")
    public List<AttributeGetResponse> indAllAttributes() {
        return attributesDataToResponse(attributeService.findAttributesByDomain());
    }

    @GetMapping("/equipment/{id}")
    public EquipmentWithAttributesGetResponse get(@PathVariable Long id) {
        EquipmentsWithDetailsData equipmentsWithDetails = equipmentService.get(id);
        return prepareEquipmentWithAttributesGetResponse(
                equipmentsWithDetails.getEquipment(), equipmentsWithDetails.getAttributes(), attributeService.findAttributesByDomain());
    }

    @DeleteMapping("/equipment/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.remove(id);
    }

    @PutMapping("/equipment/")
    public void updateEquipment(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("json") String json) {
        String fileName;
        PictureData picture = null;
        EquipmentPutRequest equipmentPutRequest;
        try{
            ObjectMapper mapper = new ObjectMapper();
            equipmentPutRequest = mapper.readValue(json, EquipmentPutRequest.class);
            System.out.println(equipmentPutRequest.getName());
            equipmentPutRequest.getValues().forEach(value -> System.out.println(value.getValue()));
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (file != null){
            if(equipmentService.get(equipmentPutRequest.getId()).getEquipment().getPicture() == null){
                fileName = fileStorageService.saveImageOnServer(file);
                picture = pictureService.createPicture(new PictureData(fileName));
            }else{
                fileName = fileStorageService.saveImageOnServer(file);
                PictureData pictureData = equipmentService.get(equipmentPutRequest.getId()).getEquipment().getPicture();
                pictureData.setPath(fileName);
                picture = pictureService.updatePicture(pictureData);
            }
        }
        EquipmentData equipment = new EquipmentData(equipmentPutRequest.getId(), userService.getLoggedUser(), picture, equipmentPutRequest.getName(), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        equipmentService.update(equipment, valuesPutRequestToData(equipmentPutRequest.getValues()));

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

    private UserData equipmentUserPutRequestToData(Long id, UserPutRequest equipmentUserPutRequest) {
        UserData userData = new UserData(id, equipmentUserPutRequest.getLogin(),
                equipmentUserPutRequest.getEmailConfirmed(), equipmentUserPutRequest.getLanguage(),
                userAuthoritiesArrayToList(equipmentUserPutRequest.getAuthorities()));
        return userData;
    }

    private List<UserAuthorityData> userAuthoritiesArrayToList(String[] authorities) {
        List<UserAuthorityData> list = new ArrayList<>();
        for (String authority : authorities) {
            list.add(new UserAuthorityData(Authority.valueOf(authority).getCodeWithRole()));
        }
        return list;
    }

    private List<UserAuthorityGetResponse> userAuthoritiesDataToGetResponse(List<UserAuthorityData> userAuthorityDataList) {
        List<UserAuthorityGetResponse> userAuthorityGetResponseList = new ArrayList<>();
        userAuthorityDataList.stream()
                .forEach(authority -> {
                    userAuthorityGetResponseList.add(new UserAuthorityGetResponse(authority.getId(),
                            userDataToGetResponse(authority.getUserData()),
                            Authority.findByCodeWithRole(authority.getAuthority()).toString()));
                });

        return userAuthorityGetResponseList;
    }

    private UserGetResponse userDataToGetResponse(UserData user) {
        return new UserGetResponse(user.getId(), user.getLogin(), user.getPasswordHash(),
                user.getEmailConfirmed(), user.getLanguage(), user.getRegistrationDate());
    }

    private List<UserGetResponse> usersDataToResponse(List<UserData> equipmentUserData) {
        List<UserGetResponse> list = new ArrayList<>();
        equipmentUserData.stream()
                .forEach(user -> {
                    list.add(new UserGetResponse(user.getId(), user.getLogin(), user.getPasswordHash(),
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

    private EquipmentWithAttributesGetResponse prepareEquipmentWithAttributesGetResponse(EquipmentData equipmentData, List<AttributeData> assignedAttributesData, List<AttributeData> attributesData) {
        List<AttributeGetResponse> assignedAttributes = new ArrayList<>();
        assignedAttributesData.stream()
                .forEach(attribute -> assignedAttributes.add(new AttributeGetResponse(attribute)));
        List<AttributeGetResponse> attributes = new ArrayList<>();
        attributesData.stream()
                .forEach(attribute -> {
                    attributes.add(new AttributeGetResponse(attribute));
                });
        return new EquipmentWithAttributesGetResponse(new EquipmentGetResponse(equipmentData.getId(), equipmentUserDataToGetResponse(equipmentData.getUserData()),
                equipmentData.getPicture() != null?pictureDataToGetResponse(equipmentData.getPicture()) : new PictureGetResponse(), equipmentData.getName(), equipmentData.getCreateDate(), equipmentData.getEditDate()),
                assignedAttributes, attributes);
    }

    private List<EquipmentGetResponse> equipmentsDataToEquipmentsGetResponse() {
        List<EquipmentGetResponse> equipments = new ArrayList<>();
        equipmentService.findAll().stream()
                .forEach(equipment -> equipments.add(new EquipmentGetResponse(equipment.getId()
                        , userDataToGetResponse(equipment.getUserData())
                        , equipment.getPicture() != null? pictureDataToGetResponse(equipment.getPicture()):new PictureGetResponse()
                        , equipment.getName()
                        , equipment.getCreateDate()
                        , equipment.getEditDate())));
        return equipments;
    }
    private PictureGetResponse pictureDataToGetResponse(PictureData picture) {
        return new PictureGetResponse(picture.getId(), picture.getPath(), picture.getCreateDate(), picture.getUpdateDate());
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
    private UserGetResponse equipmentUserDataToGetResponse(UserData userData) {
        return new UserGetResponse(userData.getId(), userData.getLogin(), userData.getPasswordHash(),
                userData.getEmailConfirmed(), userData.getLanguage(), userData.getRegistrationDate());
    }
}
