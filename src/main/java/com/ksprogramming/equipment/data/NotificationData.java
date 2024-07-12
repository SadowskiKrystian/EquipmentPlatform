package com.ksprogramming.equipment.data;

import com.ksprogramming.equipment.entities.User;

import java.time.LocalDateTime;

public class NotificationData {
    private Long id;
    private String senderLogin;
    private User receiverId;
    private String title;
    private String content;
    private LocalDateTime createDateTime;
    private LocalDateTime seenDateTime;
    private LocalDateTime deleteDateTime;

    public NotificationData() {
    }

    public NotificationData(Long id, String senderLogin, User receiverId, String title,
                            String content, LocalDateTime createDateTime, LocalDateTime seenDateTime, LocalDateTime deleteDateTime) {
        this.id = id;
        this.senderLogin = senderLogin;
        this.receiverId = receiverId;
        this.title = title;
        this.content = content;
        this.createDateTime = createDateTime;
        this.seenDateTime = seenDateTime;
        this.deleteDateTime = deleteDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public User getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(User receiverId) {
        this.receiverId = receiverId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getSeenDateTime() {
        return seenDateTime;
    }

    public void setSeenDateTime(LocalDateTime seenDateTime) {
        this.seenDateTime = seenDateTime;
    }

    public LocalDateTime getDeleteDateTime() {
        return deleteDateTime;
    }

    public void setDeleteDateTime(LocalDateTime deleteDateTime) {
        this.deleteDateTime = deleteDateTime;
    }
}
