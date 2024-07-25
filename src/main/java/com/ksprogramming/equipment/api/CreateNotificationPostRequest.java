package com.ksprogramming.equipment.api;

import java.util.List;

public class CreateNotificationPostRequest {
    private List<Long> users;
    private String title;
    private String content;

    public CreateNotificationPostRequest() {
    }

    public List<Long> getUsers() {
        return users;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
