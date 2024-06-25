package com.ksprogramming.equipment.web;

import com.ksprogramming.equipment.EquipmentApplication;
import com.ksprogramming.equipment.config.ApplicationConfig;
import com.ksprogramming.equipment.enumes.DictionaryType;
import com.ksprogramming.equipment.service.CacheService;
import com.ksprogramming.equipment.service.DictionariesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ApplicationController {
    private static final Logger log = LoggerFactory.getLogger(EquipmentApplication.class);
    private final DictionariesService dictionariesService;
    private ApplicationConfig applicationConfig;
    private CacheService cacheService;

    public ApplicationController(ApplicationConfig applicationConfig, CacheService cacheService, DictionariesService dictionariesService) {
        this.applicationConfig = applicationConfig;
        this.cacheService = cacheService;
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

    @GetMapping({"/register"})
    public String register(Model model) {
        model.addAttribute("languagesDict", dictionariesService.getDictionary(DictionaryType.LANGUAGES));
        return "register";
    }

    @GetMapping({"/registered-successfully"})
    public String registeredSuccessfully() {
        return "registered-successfully";
    }
}
