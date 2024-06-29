package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.api.CacheType;
import com.ksprogramming.equipment.api.LanguagesUtil;
import com.ksprogramming.equipment.data.DictionaryData;
import com.ksprogramming.equipment.enumes.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ksprogramming.equipment.enumes.DictionaryType.*;

@Service
@Transactional
public class DictionariesService {
    private static final Logger log = LoggerFactory.getLogger(DictionariesService.class);
    private UserServiceInterface userService;

    public DictionariesService(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Cacheable(CacheType.DICTIONARIES)
    public List<DictionaryData> getDictionary(DictionaryType type, Language lang) {
         if (YES_NO == type) {
            return getYesNo(lang);
        } else if (USER_AUTHORITIES == type) {
            return getUserAuthorities(lang);
        } else if (LANGUAGES == type) {
            return getLanguages(lang);
        } else if (EMAIL_TITLES == type) {
            return getEmailTitles(lang);
        } else if (ATTRIBUTE_TYPES == type) {
            return getAttributeTypes(lang);
        }
        throw new IllegalArgumentException("Dictionary no defined: " + type + " for language: " + lang);
    }
    public List<DictionaryData> getDictionary(DictionaryType type) {
        return getDictionary(type, LanguagesUtil.getCurrentLanguage());
    }


    public DictionaryData getDictionaryElementByCode(String code, DictionaryType type, Language lang) {
         if (YES_NO == type) {
            return getYesNo(lang).stream().filter(dictElem -> dictElem.getCode().equalsIgnoreCase(code)).findFirst().get();
        } else if (LANGUAGES == type) {
            return getLanguages(lang).stream().filter(dictElem -> dictElem.getCode().equalsIgnoreCase(code)).findFirst().get();
        }


        throw new IllegalArgumentException("Dictionary no defined: " + type + " for language: " + lang);
    }



    private List<DictionaryData> getAttributeTypes(Language lang) {
        List<DictionaryData> dictionaries = new ArrayList<>();
        dictionaries.add(new DictionaryData(AttributeType.STRING.name(), AttributeType.STRING.getName(), lang.getCode()));
        dictionaries.add(new DictionaryData(AttributeType.INT.name(), AttributeType.INT.getName(), lang.getCode()));
        dictionaries.add(new DictionaryData(AttributeType.TIME.name(), AttributeType.TIME.getName(), lang.getCode()));
        dictionaries.add(new DictionaryData(AttributeType.DOUBLE.name(), AttributeType.DOUBLE.getName(), lang.getCode()));
        dictionaries.add(new DictionaryData(AttributeType.DATE.name(), AttributeType.DATE.getName(), lang.getCode()));
        dictionaries.add(new DictionaryData(AttributeType.DATETIME.name(), AttributeType.DATETIME.getName(), lang.getCode()));
        return dictionaries;
    }
    private List<DictionaryData> getYesNo(Language lang) {
        return Language.PL == lang ?
                Arrays.asList(new DictionaryData("true", "Tak", lang.getCode()), new DictionaryData("false", "Nie", lang.getCode())) :
                Arrays.asList(new DictionaryData("true", "Yes", lang.getCode()), new DictionaryData("false", "No", lang.getCode()));
    }

    private List<DictionaryData> getUserAuthorities(Language lang) {

        List<DictionaryData> list = new ArrayList<>();

        if (Language.US == lang || Language.EN == lang) {
            list.add(new DictionaryData(Authority.ADMIN.getCode(), "Admin", lang.getCode()));
            list.add(new DictionaryData(Authority.USER.getCode(), "User", lang.getCode()));

        } else if (Language.PL == lang) {
            list.add(new DictionaryData(Authority.ADMIN.getCode(), "Admin", lang.getCode()));
            list.add(new DictionaryData(Authority.USER.getCode(), "Użytkownik", lang.getCode()));
        }

        return list;
    }
    private List<DictionaryData> getLanguages(Language lang) {
        List<DictionaryData> list = new ArrayList<>();

        if (Language.US == lang || Language.EN == lang) {
            list.add(new DictionaryData("en", "English", lang.getCode()));
            list.add(new DictionaryData("pl", "Polish", lang.getCode()));
        } else if (Language.PL == lang) {
            list.add(new DictionaryData("en", "Angielski", lang.getCode()));
            list.add(new DictionaryData("pl", "Polski", lang.getCode()));
        }

        return list;
    }


    private List<DictionaryData> getEmailTitles(Language lang) {
        List<DictionaryData> list = new ArrayList<>();

        if (Language.US == lang || Language.EN == lang) {
            list.add(new DictionaryData(EmailTitle.AFTER_REGISTRATION.getCode(), "Registration confirmation", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.AFTER_PASSWORD_CHANGE.getCode(), "Password has been changed", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.PASSWORD_RESET.getCode(), "Password reset", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.EMAIL_CONFIRMATION_LINK.getCode(), "Confirm your email", lang.getCode()));

        } else if (Language.PL == lang) {
            list.add(new DictionaryData(EmailTitle.AFTER_REGISTRATION.getCode(), "Potwierdzenie rejestracji", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.AFTER_PASSWORD_CHANGE.getCode(), "Hasło zostało zmienione", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.PASSWORD_RESET.getCode(), "Resetowanie hasła", lang.getCode()));
            list.add(new DictionaryData(EmailTitle.EMAIL_CONFIRMATION_LINK.getCode(), "Potwierdź swój email", lang.getCode()));
        }

        return list;
    }
}
