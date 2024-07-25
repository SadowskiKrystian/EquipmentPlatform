package com.ksprogramming.equipment.auth;

import com.ksprogramming.equipment.data.UserData;
import com.ksprogramming.equipment.enumes.Authority;
import com.ksprogramming.equipment.enumes.Language;
import com.ksprogramming.equipment.api.LanguagesUtil;
import com.ksprogramming.equipment.service.UserAuthorityServiceInterface;
import com.ksprogramming.equipment.service.UserServiceInterface;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AuthenticatedUser {
    private UserServiceInterface userService;
    private UserAuthorityServiceInterface userAuthorityService;

    public AuthenticatedUser(UserServiceInterface userService, UserAuthorityServiceInterface userAuthorityService) {
        this.userService = userService;
        this.userAuthorityService = userAuthorityService;
    }

    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }
    public List<Menu> getMenu() {
        UserData loggedUser = userService.getLoggedUser();
        Language currentLang = LanguagesUtil.getCurrentLanguage();

        if (userAuthorityService.hasCustomerAuthority(loggedUser, Authority.ADMIN)) {
        return new ArrayList<>(Arrays.asList(
                new Menu("Equipment", "#", new ArrayList<>(Arrays.asList(
                        new Menu(chooseMenuName("Users", "Użytkownicy", currentLang), "/admin/users", Collections.emptyList()),
                        new Menu(chooseMenuName("Equipments", "Wyposażenie", currentLang), "/admin/equipments", Collections.emptyList()),
                        new Menu(chooseMenuName("Attributes", "Atrybuty", currentLang), "/admin/attributes", Collections.emptyList())
                ))),
                new Menu(chooseMenuName("Notifications", "Powiadomienia", currentLang), "#", new ArrayList<>(Arrays.asList(
                        new Menu(chooseMenuName("Show sent notifications", "Pokaz wyslane powiadomienia", currentLang), "/admin/notifications", Collections.emptyList()),
                        new Menu(chooseMenuName("Send notifications", "Wyślij powiadomienia", currentLang), "/admin/notification/send", Collections.emptyList())
                ))),
                new Menu(chooseMenuName("Contacts", "Kontakty", currentLang), "#", new ArrayList<>(Arrays.asList(
                        new Menu(chooseMenuName("People", "Ludzie", currentLang), "/admin/contacts/people", Collections.emptyList())
                )))
        ));
    } else {
        throw new IllegalArgumentException("You don't have permissions to Menu!");
    }
}
    private String chooseMenuName(String englishName, String polishName, Language lang) {

        if (lang == Language.PL) {
            return polishName;
        } else {
            return englishName;
        }

    }

}
