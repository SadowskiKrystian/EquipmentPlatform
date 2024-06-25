package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.enumes.Language;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class LanguagesUtil {

    public static com.ksprogramming.equipment.enumes.Language getCurrentLanguage() {
        return prepareLanguageFrom(LocaleContextHolder.getLocale());
    }

    public static com.ksprogramming.equipment.enumes.Language prepareLanguageFrom(Locale locale) {
        return Language.valueOf(locale.getLanguage().toUpperCase());
    }

}
