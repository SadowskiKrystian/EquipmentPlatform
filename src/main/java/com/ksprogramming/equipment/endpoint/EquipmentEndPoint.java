package com.ksprogramming.equipment.endpoint;

import com.ksprogramming.equipment.api.*;
import com.ksprogramming.equipment.data.*;
import com.ksprogramming.equipment.enumes.Authority;
import com.ksprogramming.equipment.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private EmailServiceInterface emailService;
    private TokenServiceInterface tokenService;
    private NotificationServiceInterface notificationService;
    private FileStorageServiceInterface fileStorageService;
    public EquipmentEndPoint(UserServiceInterface userService, EquipmentServiceInterface equipmentService,
                             AttributeServiceInterface attributeService, AssignedAttributeServiceInterface assignedAttributeService, EmailServiceInterface emailService, TokenServiceInterface tokenService, NotificationServiceInterface notificationService, FileStorageServiceInterface fileStorageService) {
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.attributeService = attributeService;
        this.assignedAttributeService = assignedAttributeService;
        this.emailService = emailService;
        this.tokenService = tokenService;
        this.notificationService = notificationService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/image")
    public void uploadImage(@RequestParam("file") MultipartFile file){
        fileStorageService.saveImageOnServer(file);
    }
    @GetMapping("/notifications")
    public List<NotificationGetResponse> findNotificationByReceiverId() {
       return notificationsDataToResponse(notificationService.findNotificationsByReceiverId());
    }
    @PutMapping("/notification/{id}")
    public void updateNotification(@PathVariable("id") Long id) {
        notificationService.updateSeenNotification(id);
    }
    @DeleteMapping("/notification/{id}")
    public void deleteNotification(@PathVariable("id") Long id) {
        notificationService.deleteNotification(id);
    }
    @GetMapping("notifications/count/unseen")
    public Long countUnseenNotifications() {
        return notificationService.countUnseenNotifications();
    }

    @PostMapping("/register-user")
    public void register(@RequestBody UserPostRequest request, HttpServletRequest httpServletRequest) {
        List<UserAuthorityData> authorities = new ArrayList<>();
        authorities.add(new UserAuthorityData(Authority.USER.getCode()));
        UserData user = new UserData(request.getLogin(), request.getPasswordHash(), false, request.getLanguage(),
                authorities, LocalDateTime.now());
        UserData createUser = userService.registerUser(user);
        TokenData token = tokenService.createToken(createUser);
        emailService.sendEmailVerificationMessage(new EmailData(user.getLogin()), token);
    }
    @PostMapping("/verify")
    public void verifyAccount(@RequestBody TokenPostRequest token) {
        TokenData findToken = tokenService.findToken(token.getSendToken());
        UserData userByEmail = userService.getUserByEmail(findToken.getUserData().getLogin());
        findToken.setUsed(true);
        tokenService.updateToken(findToken);
        userByEmail.setEmailConfirmed(true);
        userService.update(userByEmail);
    }
    @PostMapping("/user/forget-password/")
    public void forgetPassword(@RequestParam String email) {
            UserData user = userService.getUserByEmail(email);
            TokenData token = tokenService.createToken(user);
            emailService.sendResetPasswordEmail(new EmailData(user.getLogin()), token);

    }
    @PutMapping("/user/reset-password")
    public void resetPassword(@RequestBody ResetForgottenPasswordPutRequest request){
        TokenData token = tokenService.findToken(request.getToken());
        UserData user = userService.getUserByEmail(token.getUserData().getLogin());
        user.setPasswordHash(request.getPasswordHash());
        userService.changeForgottenPassword(user);
        token.setUsed(true);
        tokenService.updateToken(token);
    }

    @GetMapping("equipment/{id}")
    public EquipmentWithAttributesGetResponse getEquipment(@PathVariable Long id) {
        EquipmentsWithDetailsData equipmentWithDetails = equipmentService.get(id);
        return prepareEquipmentWithAttributesGetResponse(
                    equipmentWithDetails.getEquipment(), equipmentWithDetails.getAttributes(), attributeService.findAttributesByDomain());

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
    public List<EquipmentGetResponse> findAll(@RequestParam(name = "name", required = false) String name) {
        return equipmentsDataToEquipmentsGetResponse(name);
    }
    @GetMapping("/equipments/attributes")
    public List<AttributeGetResponse> findAllAttributes() {
        return attributesDataToResponse(attributeService.findAttributesByDomain());
    }
    private List<NotificationGetResponse> notificationsDataToResponse(List<NotificationData> notificationsByReceiverId) {
        List<NotificationGetResponse> notifications = new ArrayList<>();
        notificationsByReceiverId.forEach(notification -> notifications.add(new NotificationGetResponse(notification.getId(), notification.getSenderLogin(), userDataToResponse(notification.getReceiverId()),
                notification.getTitle(), notification.getContent(), notification.getCreateDateTime(), notification.getSeenDateTime())));
        return notifications;
    }
    private UserGetResponse userDataToResponse(UserData user) {
        return new UserGetResponse(user.getId(), user.getLogin(), user.getPasswordHash(),
                user.getEmailConfirmed(), user.getLanguage(), user.getRegistrationDate());
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
    private List<EquipmentGetResponse> equipmentsDataToEquipmentsGetResponse(String name) {
        List<EquipmentGetResponse> equipments = new ArrayList<>();
        equipmentService.findByLogin(name).stream()
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
    private UserGetResponse equipmentUserDataToGetResponse(UserData userData) {
        return new UserGetResponse(userData.getId(), userData.getLogin(), userData.getPasswordHash(),
                userData.getEmailConfirmed(), userData.getLanguage(), userData.getRegistrationDate());
    }
}
