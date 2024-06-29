package com.ksprogramming.equipment.data;

import java.math.BigDecimal;

public class DictionaryData {
    private Long id;
    private String code;
    private String value;
    private String language;
    private Long extraId;
    private BigDecimal extraPrice;
    private String extraString;


    public DictionaryData(Long id, String code, String value, String language) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.language = language;
    }

    public DictionaryData(Long id, String value, String language) {
        this.id = id;
        this.value = value;
        this.language = language;
    }

    public DictionaryData(Long id, String value, String language, String extraString, String nothing) {
        this.id = id;
        this.value = value;
        this.language = language;
        this.extraString = extraString;
    }

    public DictionaryData(Long id, String value, String language, BigDecimal extraPrice) {
        this.id = id;
        this.value = value;
        this.language = language;
        this.extraPrice = extraPrice;
    }

    public DictionaryData(Long id, String value, String language, Long extraId) {
        this.id = id;
        this.value = value;
        this.language = language;
        this.extraId = extraId;
    }

    public DictionaryData(String code, String value, String language) {
        this.code = code;
        this.value = value;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getLanguage() {
        return language;
    }

    public Long getExtraId() {
        return extraId;
    }

    public BigDecimal getExtraPrice() {
        return extraPrice;
    }

    public String getExtraString() {
        return extraString;
    }
}
