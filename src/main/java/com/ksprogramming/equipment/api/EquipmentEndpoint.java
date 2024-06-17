package com.ksprogramming.equipment.api;

import com.ksprogramming.equipment.api.courseplatform.*;
import com.ksprogramming.equipment.domain.courseplatform.*;
import com.ksprogramming.equipment.domain.equipment.*;
import com.ksprogramming.equipment.logic.courseplatform.*;
import com.ksprogramming.equipment.logic.courseplatform.notifications.NotificationType;
import com.ksprogramming.equipment.logic.courseplatform.notifications.NotificationsService;
import com.ksprogramming.equipment.logic.equipment.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/api/crs")
public class EquipmentEndpoint {

    private final EquipmentUserServiceInterface equipmentUserService;
    private CourseCustomersService courseCustomersService;
    private PasswordResetTokensService passwordResetTokensService;
    private ModulesService modulesService;
    private EmailConfirmationService emailConfirmationService;
    private NotificationsService notificationsService;
    private EquipmentServiceInterface equipmentService;
    private AttributeServiceInterface attributeService;
    private AssignedAttributeServiceInterface assignedAttributeService;

    public EquipmentEndpoint(CourseCustomersService courseCustomersService, PasswordResetTokensService passwordResetTokensService,
                             ModulesService modulesService, EmailConfirmationService emailConfirmationService,
                             NotificationsService notificationsService, EquipmentServiceInterface equipmentService, AttributeServiceInterface attributeService,
                             AssignedAttributeServiceInterface assignedAttributeService, EquipmentUserServiceInterface equipmentUserService) {
        this.courseCustomersService = courseCustomersService;
        this.passwordResetTokensService = passwordResetTokensService;
        this.modulesService = modulesService;
        this.emailConfirmationService = emailConfirmationService;
        this.notificationsService = notificationsService;
        this.equipmentService = equipmentService;
        this.attributeService = attributeService;
        this.assignedAttributeService = assignedAttributeService;
        this.equipmentUserService = equipmentUserService;
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
        EquipmentData equipment = new EquipmentData(equipmentUserService.getLoggedUser(), request.getName(), LocalDateTime.now());
        equipmentService.update(equipment, valuesPutRequestToData(request.getValues()));
    }
    @PostMapping("/equipment")
    public void createEquipment(@RequestBody EquipmentPostRequest equipmentPostRequest) {
        EquipmentData equipment = new EquipmentData(equipmentUserService.getLoggedUser(), equipmentPostRequest.getName(), LocalDateTime.now());
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

    @PostMapping("/register-customer")
    public void register(@RequestBody CustomerPostRequest request, HttpServletRequest httpServletRequest) {
        CustomerData customerCreateRequest = new CustomerData(request.getLogin(), request.getPasswordHash(), request.getLanguage(), request.getRegulationAccepted(), request.getNewsletterAccepted(), request.getIsEnabled(), request.getIsEmailConfirmed(), httpServletRequest, new String[]{CustomerAuthority.USER.getCode()});
        courseCustomersService.registerCustomer(customerCreateRequest);
    }

    @PostMapping("/register-teacher")
    public void registerTeacher(@RequestBody CustomerPostRequest request, HttpServletRequest httpServletRequest) {
        CustomerData customerCreateRequest = new CustomerData(request.getLogin(), request.getPasswordHash(), request.getLanguage(),
                request.getRegulationAccepted(), request.getNewsletterAccepted(), request.getAuthorFirstName(), request.getAuthorLastName(), httpServletRequest, new String[]{CustomerAuthority.TEACHER.getCode()});
        courseCustomersService.registerTeacher(customerCreateRequest);
    }

    @PostMapping("/register-student")
    public void registerStudent(@RequestBody CustomerPostRequest request, HttpServletRequest httpServletRequest) {
        CustomerData customerCreateRequest = new CustomerData(request.getLogin(), request.getPasswordHash(), request.getLanguage(), request.getRegulationAccepted(), request.getNewsletterAccepted(), request.getIsEnabled(), request.getIsEmailConfirmed(), httpServletRequest, new String[]{CustomerAuthority.STUDENT.getCode()});
        courseCustomersService.registerCustomer(customerCreateRequest);
    }



    @PostMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordRequest request) {
        courseCustomersService.changeLoggedUsersPassword(request.getActualPasswordHash(), request.getNewPasswordHash());
    }

    @PutMapping("/logged-customer")
    public void updateLoggedCustomer(@RequestBody LoggedCustomerPutRequest request) {
        CustomerData loggedCustomer = courseCustomersService.getLoggedCustomer();

        if (loggedCustomer == null) {
            throw new IllegalArgumentException("Your are logged out. Please log in and retry");
        }

        courseCustomersService.updateLoggedCustomer(request.getLanguage(), request.getNewsletterAccepted());
    }



    @PostMapping("/customer/forget-password")
    public void forgetPassword(@RequestParam String email) {
        passwordResetTokensService.prepareAndSendToken(email);
    }

    @PutMapping("/customer/reset-password")
    public void resetPassword(@RequestBody ResetPasswordPutRequest request) {
        passwordResetTokensService.resetPassword(request.getToken(), request.getPasswordHash());
    }


    @PostMapping("/send/email-confirmation-link")
    public void sendConfirmationEmail() {
        emailConfirmationService.sendEmailConfirmationLink();
    }

    @GetMapping("/notifications/count/unseen")
    public UnseenNotificationsCountRequestGetResponse getUnseenNotifications() {

        CustomerData customer = courseCustomersService.getLoggedCustomer();
        if (customer == null) {
            throw new IllegalArgumentException("You must be logged to get your unseen notifications count");
        }

        return new UnseenNotificationsCountRequestGetResponse(notificationsService.unseenNotificationsCount(customer));
    }

    @GetMapping("/notifications")
    public NotificationsRequestGetResponse getNotifications() {

        CustomerData customer = courseCustomersService.getLoggedCustomer();
        if (customer == null) {
            throw new IllegalArgumentException("You must be logged to get your notifications");
        }

        List<NotificationData> notifications = notificationsService.find(new NotificationsFilter(false, customer, NotificationType.PLATFORM, 10L));
        notificationsService.setNotificationsSeen(notifications);
        return new NotificationsRequestGetResponse(notificationsToResponses(notifications));
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
                        , equipmentUserDataToGetResponse(equipment.getEquipmentUserData())
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
    private EquipmentUserGetResponse equipmentUserDataToGetResponse(EquipmentUserData equipmentUserData) {
        return new EquipmentUserGetResponse(equipmentUserData.getId(), equipmentUserData.getLogin(), equipmentUserData.getPasswordHash(),
                equipmentUserData.getEmailConfirmed(), equipmentUserData.getLanguage(), equipmentUserData.getRegistrationDate());
    }
    private List<NotificationGetResponse> notificationsToResponses(List<NotificationData> notifications) {

        List<NotificationGetResponse> responses = new ArrayList<>();

        for (NotificationData notification : notifications) {

            LocalDateTime dateTime = notification.getCreateDatetime();

            String createDatetime = dateTime.getHour() + ":" + dateTime.getMinute() + " " + dateTime.getDayOfMonth() + "." + dateTime.getMonthValue() + "." + dateTime.getYear();
            String title = notification.getTitle();
            String content = notification.getContent();
            String link = notification.getLink();
            boolean seen = notification.getSeenDatetime() == null;

            responses.add(new NotificationGetResponse(createDatetime, title, content, link, seen));
        }

        return responses;
    }
}
