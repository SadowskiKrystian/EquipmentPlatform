package com.ksprogramming.equipment.api;

import com.ksprogramming.equipment.entities.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificationGetResponse {
    private Long id;
    private String senderLogin;
    private User receiverId;
    private String title;
    private String content;
    private String createDate;
    private String seenDateTime;
    private DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public NotificationGetResponse() {
    }

    public NotificationGetResponse(Long id, String senderLogin, User receiverId, String title, String content, LocalDateTime createDateTime, LocalDateTime seenDateTime) {
        this.id = id;
        this.senderLogin = senderLogin;
        this.receiverId = receiverId;
        this.title = title;
        this.content = content;
        this.createDate = createDateTime.format(formatter);
        this.seenDateTime = seenDateTime!=null?seenDateTime.format(formatter):"";
    }

    public Long getId() {
        return id;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public User getReceiverId() {
        return receiverId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getSeenDateTime() {
        return seenDateTime;
    }
}
