package com.ksprogramming.equipment.web;

import com.ksprogramming.equipment.EquipmentApplication;
import com.ksprogramming.equipment.config.ApplicationConfig;
import com.ksprogramming.equipment.enumes.DictionaryType;
import com.ksprogramming.equipment.service.DictionariesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class ApplicationController {
    private static final Logger log = LoggerFactory.getLogger(EquipmentApplication.class);
    private final DictionariesService dictionariesService;
    private ApplicationConfig applicationConfig;


    public ApplicationController(ApplicationConfig applicationConfig, DictionariesService dictionariesService) {
        this.applicationConfig = applicationConfig;
        this.dictionariesService = dictionariesService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "home-page";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping({"/forget-password"})
    public String forgetPassword() {
        return "forget-password";
    }
    @GetMapping({"/reset-password"})
    public String resetPassword(@RequestParam(value = "token", defaultValue = "empty") String token){
        return "front-reset-password";
    }

    @GetMapping({"/register"})
    public String register(Model model) {
        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES));
        return "register";
    }
    @GetMapping("/verify")
    public String verifyEmail(@RequestParam(value = "token", defaultValue = "empty") String token) {
        if (token.equals("empty")) {
            return "redirect:/login";
        }
        return "email-confirmation";
    }

    @GetMapping({"/registered-successfully"})
    public String registeredSuccessfully() {
        return "registered-successfully";
    }

    @GetMapping("/create-equipment-front")
    public String createEquipment(){
        return "create-equipment-front";
    }

    @GetMapping("/equipments-front")
    public String printFrontEquipments(Model model) {
        return "equipments-front";
    }

    @GetMapping("/equipment-front/{id}")
    public String getEquipment(@PathVariable Long id, Model model){
        model.addAttribute("equipmentId", id);
        return "equipment-front";
    }
}
