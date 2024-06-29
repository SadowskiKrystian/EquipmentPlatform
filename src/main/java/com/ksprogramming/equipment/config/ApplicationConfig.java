package com.ksprogramming.equipment.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableCaching
@EnableScheduling
public class ApplicationConfig {

    @Value("${app.name.panel}")
    private String appNamePanel;
    @Value("${favicon.path}")
    private String faviconPath;
    @Value("${app.name.front}")
    private String appNameFront;
    @Value("${logo.front.path}")
    private String logoFrontPath;
    @Value("${logo.panel.path}")
    private String logoPanelPath;

    public String getFaviconPath() {
        return faviconPath;
    }

    public void setFaviconPath(String faviconPath) {
        this.faviconPath = faviconPath;
    }

    public String getAppNameFront() {
        return appNameFront;
    }

    public void setAppNameFront(String appNameFront) {
        this.appNameFront = appNameFront;
    }

    public String getLogoFrontPath() {
        return logoFrontPath;
    }

    public void setLogoFrontPath(String logoFrontPath) {
        this.logoFrontPath = logoFrontPath;
    }

    public String getLogoPanelPath() {
        return logoPanelPath;
    }

    public void setLogoPanelPath(String logoPanelPath) {
        this.logoPanelPath = logoPanelPath;
    }

    public String getAppNamePanel() {
        return appNamePanel;
    }

    public void setAppNamePanel(String appNamePanel) {
        this.appNamePanel = appNamePanel;
    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate =  new RestTemplate();

        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
}
