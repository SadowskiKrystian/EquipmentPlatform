package com.ksprogramming.equipment.api;

import com.ksprogramming.equipment.api.courseplatform.*;
import com.ksprogramming.equipment.domain.DictionaryData;
import com.ksprogramming.equipment.domain.courseplatform.*;
import com.ksprogramming.equipment.domain.equipment.*;
import com.ksprogramming.equipment.logic.DictionariesService;
import com.ksprogramming.equipment.logic.DictionaryType;
import com.ksprogramming.equipment.logic.Language;
import com.ksprogramming.equipment.logic.courseplatform.*;
import com.ksprogramming.equipment.logic.courseplatform.notifications.NotificationsFromPanelService;
import com.ksprogramming.equipment.logic.equipment.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.ksprogramming.equipment.logic.courseplatform.ConverterToResponsesUtil.statisticNewCustomersToResponses;


@RestController
@RequestMapping("/admin/api/crs")
public class EquipmentEndpointAdmin {


    private StatisticService statisticService;
    private ModulesService modulesService;
    private MarketingService marketingService;
    private NotificationsFromPanelService notificationsFromPanelService;
    private CourseCustomersService courseCustomersService;
    private ContactsService contactsService;
    private TraceService traceService;
    private EquipmentServiceInterface equipmentService;
    private AssignedAttributeService assignedAttributeService;
    private AttributeServiceInterface attributeService;
    private DictionariesService dictionariesService;
    private EquipmentUserServiceInterface equipmentUserService;
    private UserAuthorityServiceInterface userAuthorityService;


    public EquipmentEndpointAdmin(CourseAttachmentsService courseAttachmentsService, StatisticService statisticService,
                                  ModulesService modulesService, MarketingService marketingService, NotificationsFromPanelService notificationsFromPanelService,
                                  CourseCustomersService courseCustomersService, ContactsService contactsService, TraceService traceService,
                                  EquipmentServiceInterface equipmentService, AssignedAttributeService assignedAttributeService, AttributeServiceInterface attributeService,
                                  DictionariesService dictionariesService, EquipmentUserServiceInterface equipmentUserService, UserAuthorityServiceInterface userAuthorityService) {
        this.statisticService = statisticService;
        this.modulesService = modulesService;
        this.marketingService = marketingService;
        this.notificationsFromPanelService = notificationsFromPanelService;
        this.courseCustomersService = courseCustomersService;
        this.contactsService = contactsService;
        this.traceService = traceService;
        this.equipmentService = equipmentService;
        this.assignedAttributeService = assignedAttributeService;
        this.attributeService = attributeService;
        this.dictionariesService = dictionariesService;
        this.equipmentUserService = equipmentUserService;
        this.userAuthorityService = userAuthorityService;
    }

    @GetMapping("/equipment-users")
    public List<EquipmentUserGetResponse> findAllEquipmentUsers() {
        return equipmentUsersDataToResponse(equipmentUserService.findAll());
    }

    @PostMapping("/equipment-users")
    public void createUser(@RequestBody EquipmentUserPostRequest request, HttpServletRequest httpServletRequest) {
        equipmentUserService.registerUser(new EquipmentUserData(
                request.getLogin(),
                request.getPasswordHash(),
                false,
                request.getLanguage(),
                userAuthoritiesArrayToList(request.getAuthorities()),
                LocalDateTime.now()));
    }
    @PutMapping("/equipment-user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody EquipmentUserPutRequest equipmentUserPutRequest) {
        equipmentUserService.update(equipmentUserPutRequestToData(id, equipmentUserPutRequest));
    }


    @DeleteMapping("/equipment-user/{id}")
    public void deleteUser(@PathVariable Long id) {
        equipmentUserService.delete(id);
    }
    @PutMapping("/equipment-user-change-password/{id}")
    public void changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request) {
        equipmentUserService.changePasswordAdmin(id, request.getNewPasswordHash());
    }
    @GetMapping("/authority/{id}")
    public List<UserAuthorityGetResponse> findAuthorityById(@PathVariable Long id) {
        return userAuthoritiesDataToGetResponse(userAuthorityService.findById(id));
    }

    @GetMapping("/customers")
    public List<CustomerGetResponse> getCustomers(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize,
            @RequestParam(name = "login", required = false) String loginLike,
            @RequestParam(name = "invoice_first_and_last_name", required = false) String invoiceFirstAndLastName
    ) {
        List<CustomerData> customers = courseCustomersService.find(new CustomersFilter(loginLike, page, pageSize, invoiceFirstAndLastName));
        return customersToResponses(customers);
    }



    @GetMapping("/attribute/{id}")
    public AttributeWithDetailsGetResponse getAttribute(@PathVariable Long id) {
        return new AttributeWithDetailsGetResponse(attributeDataToResponse(attributeService.getAttribute(id)), assignedAttributeDataToResponse(assignedAttributeService.getAttributeWithValues(id)));
    }

    @GetMapping("/attributes")
    public AttributesWithDetailsGetResponse findAttributes() {
        return new AttributesWithDetailsGetResponse(attributesDataToResponse(attributeService.findAttributes()), AttributeType.values());
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

    @GetMapping("/equipment/{id}")
    public EquipmentWithAttributesGetResponse get(@PathVariable Long id) {
        EquipmentsWithDetailsData equipmentsWithDetails = equipmentService.get(id);
        return prepareEquipmentWithAttributesGetResponse(equipmentsWithDetails.getEquipment(), equipmentsWithDetails.getAttributes(), attributeService.findAttributesByDomain());
    }

    @DeleteMapping("/equipment/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.remove(id);
    }

    @PutMapping("/equipment/{id}")
    public void updateEquipment(@PathVariable Long id, @RequestBody EquipmentPutRequest request) {
        EquipmentData equipment = new EquipmentData(equipmentUserService.getLoggedUser(), request.getName(), LocalDateTime.now());
        equipmentService.update(equipment, valuesPutRequestToData(request.getValues()));

    }

    @PutMapping("/customer/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody CustomerPutRequest request) {
        CustomerData data = courseCustomersService.get(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        courseCustomersService.update(new CustomerData(id, request.getLogin(), data.getPasswordHash(), request.getLanguage(),
                request.getInvoiceType(), request.getInvoiceCompanyName(), request.getInvoiceStreet(), request.getInvoicePostalCode(),
                request.getInvoiceCity(), request.getInvoiceNip(), request.getInvoiceCountry(), request.getRegulationAccepted(),
                request.getNewsletterAccepted(), request.getInvoiceFirstAndLastName(),
                request.getIsEnabled(), request.getIsEmailConfirmed(), LocalDateTime.parse(request.getRegistrationDatetime(), formatter),
                request.getAuthorities()));
    }


    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        courseCustomersService.delete(id);
    }

    @GetMapping("/statistic/new-customers")
    public List<StatisticNewCustomerGetResponse> newCustomers() {
        return statisticNewCustomersToResponses(statisticService.findNewCustomers());
    }

    @PostMapping("/notifications")
    public void createNotifications(@RequestBody NotificationsPostRequest request) {
        notificationsFromPanelService.createNotifications(new CreateNotificationsParameters(request));
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

    private EquipmentUserData equipmentUserPutRequestToData(Long id, EquipmentUserPutRequest equipmentUserPutRequest) {
        EquipmentUserData equipmentUserData = new EquipmentUserData(id, equipmentUserPutRequest.getLogin(),
                equipmentUserPutRequest.getEmailConfirmed(), equipmentUserPutRequest.getLanguage(),
                userAuthoritiesArrayToList(equipmentUserPutRequest.getAuthorities()));
        return equipmentUserData;
    }

    private List<UserAuthorityData> userAuthoritiesArrayToList(String[] authorities) {
        List<UserAuthorityData> list = new ArrayList<>();
        for(String authority : authorities){
            list.add(new UserAuthorityData(CustomerAuthority.valueOf(authority).getCodeWithRole()));
        }
        return list;
    }

    private List<UserAuthorityGetResponse> userAuthoritiesDataToGetResponse(List<UserAuthorityData> userAuthorityDataList) {
        List<UserAuthorityGetResponse> userAuthorityGetResponseList = new ArrayList<>();
        userAuthorityDataList.stream()
                .forEach(authority -> {
                    userAuthorityGetResponseList.add(new UserAuthorityGetResponse(authority.getId(),
                            equipmentUserDataToGetResponse(authority.getEquipmentUserData()),
                            CustomerAuthority.findByCodeWithRole(authority.getAuthority()).toString()));
                });

        return userAuthorityGetResponseList;
    }

    private EquipmentUserGetResponse equipmentUserDataToGetResponse(EquipmentUserData equipmentUserData) {
        return new EquipmentUserGetResponse(equipmentUserData.getId(), equipmentUserData.getLogin(), equipmentUserData.getPasswordHash(),
                equipmentUserData.getEmailConfirmed(), equipmentUserData.getLanguage(), equipmentUserData.getRegistrationDate());
    }

    private List<EquipmentUserGetResponse> equipmentUsersDataToResponse(List<EquipmentUserData> equipmentUserData) {
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

    private List<CustomerGetResponse> customersToResponses(List<CustomerData> customers) {
        List<CustomerGetResponse> list = new ArrayList<>();
        for (CustomerData customer : customers) {
            list.add(new CustomerGetResponse(customer));
        }
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
        equipmentService.findAll().stream()
                .forEach(equipment -> equipments.add(new EquipmentGetResponse(equipment.getId()
                        , equipmentUserDataToGetResponse(equipment.getEquipmentUserData())
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
