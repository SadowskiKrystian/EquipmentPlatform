package com.ksprogramming.equipment.web;

import com.ksprogramming.equipment.EquipmentApplication;
import com.ksprogramming.equipment.config.ApplicationConfig;
import com.ksprogramming.equipment.service.CacheService;
import com.ksprogramming.equipment.service.CacheType;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ApplicationController {
    private static final Logger log = LoggerFactory.getLogger(EquipmentApplication.class);
    private ApplicationConfig applicationConfig;
    private CacheService cacheService;

    public ApplicationController(ApplicationConfig applicationConfig, CacheService cacheService) {
        this.applicationConfig = applicationConfig;
        this.cacheService = cacheService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "home-page";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
