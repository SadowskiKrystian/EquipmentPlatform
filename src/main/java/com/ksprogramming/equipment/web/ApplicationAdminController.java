package com.ksprogramming.equipment.web;

import com.ksprogramming.equipment.api.LanguagesUtil;
import com.ksprogramming.equipment.enumes.DictionaryType;
import com.ksprogramming.equipment.enumes.Language;
import com.ksprogramming.equipment.service.DictionariesService;
import com.ksprogramming.equipment.service.UserServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ApplicationAdminController {
    private final DictionariesService dictionariesService;
    private UserServiceInterface userService;

    public ApplicationAdminController(DictionariesService dictionariesService, UserServiceInterface userService) {
        this.dictionariesService = dictionariesService;
        this.userService = userService;
    }

    @GetMapping({""})
    public String admin() {
        return "admin";
    }
    @GetMapping({"/notifications"})
    public String notifications(Model model) {
        return "notifications-admin";
    }
    @GetMapping({"/notification/send"})
    public String notificationCreate() {
        return "create-notification";
    }
    @GetMapping({"/equipments"})
    public String equipments(Model model) {
        return "equipments-admin";
    }
    @GetMapping({"/equipment/{id}"})
    public String equipment(@PathVariable Long id, Model model) {
        model.addAttribute("equipmentId",  id);
        return "equipment-admin";
    }
    @GetMapping({"/attributes"})
    public String attributes(Model model) {
        return "attributes-admin";
    }
    @GetMapping({"/attribute/{id}"})
    public String getAttribute(@PathVariable Long id, Model model) {
        model.addAttribute("attributeId",  id);
        return "attribute-admin";
    }
    @GetMapping({"/users"})
    public String adminCustomers(Model model) {
        Language currentLang = LanguagesUtil.getCurrentLanguage();
        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, currentLang));
        model.addAttribute("yesNoDict", dictionariesService.getDictionary(DictionaryType.YES_NO, currentLang));
        model.addAttribute("authoritiesDict", dictionariesService.getDictionary(DictionaryType.USER_AUTHORITIES, currentLang));
        return "users-admin";
    }

    @GetMapping({"/user/{id}"})
    public String customer(Model model, @PathVariable Long id) {
        Language currentLang = LanguagesUtil.getCurrentLanguage();
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("authoritiesDict", dictionariesService.getDictionary(DictionaryType.USER_AUTHORITIES, currentLang));
        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES, currentLang));
        model.addAttribute("yesNoDict", dictionariesService.getDictionary(DictionaryType.YES_NO, currentLang));
        return "user-admin";
    }

}
