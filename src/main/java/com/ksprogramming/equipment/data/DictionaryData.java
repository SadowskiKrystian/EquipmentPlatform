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

    public DictionaryData(Long id, String code, String value, String language, Long extraId, BigDecimal extraPrice, String extraString) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.language = language;
        this.extraId = extraId;
        this.extraPrice = extraPrice;
        this.extraString = extraString;
    }

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

    public static DictionaryDataBuilder builder(){
        return new DictionaryDataBuilder();
    }

    public static class DictionaryDataBuilder {
        private Long id;
        private String code;
        private String value;
        private String language;
        private Long extraId;
        private BigDecimal extraPrice;
        private String extraString;

        public DictionaryDataBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DictionaryDataBuilder code(String code) {
            this.code = code;
            return this;
        }

        public DictionaryDataBuilder value(String value) {
            this.value = value;
            return this;
        }

        public DictionaryDataBuilder language(String language) {
            this.language = language;
            return this;
        }

        public DictionaryDataBuilder extraId(Long extraId) {
            this.extraId = extraId;
            return this;
        }

        public DictionaryDataBuilder extraPrice(BigDecimal extraPrice) {
            this.extraPrice = extraPrice;
            return this;
        }

        public DictionaryDataBuilder extraString(String extraString) {
            this.extraString = extraString;
            return this;
        }

        public DictionaryData build() {
            return new DictionaryData(id, code, value, language, extraId, extraPrice, extraString);
        }
    }
}
