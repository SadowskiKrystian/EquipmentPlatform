package com.ksprogramming.equipment.api;

import com.ksprogramming.equipment.entities.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificationGetResponse {
    private Long id;
    private String senderLogin;
    private UserGetResponse receiverId;
    private String title;
    private String content;
    private String createDate;
    private String seenDateTime;
    private DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public NotificationGetResponse() {
    }

    public NotificationGetResponse(Long id, String senderLogin, UserGetResponse receiverId, String title, String content, LocalDateTime createDateTime, LocalDateTime seenDateTime) {
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

    public UserGetResponse getReceiverId() {
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

    public static NotificationGetResponseBuilder builder(){
        return new NotificationGetResponseBuilder();
    }

    public static class NotificationGetResponseBuilder {
        private Long id;
        private String senderLogin;
        private UserGetResponse receiverId;
        private String title;
        private String content;
        private LocalDateTime createDate;
        private LocalDateTime seenDateTime;

        public NotificationGetResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public NotificationGetResponseBuilder senderLogin(String senderLogin) {
            this.senderLogin = senderLogin;
            return this;
        }

        public NotificationGetResponseBuilder receiverId(UserGetResponse receiverId) {
            this.receiverId = receiverId;
            return this;
        }

        public NotificationGetResponseBuilder title(String title) {
            this.title = title;
            return this;
        }

        public NotificationGetResponseBuilder content(String content) {
            this.content = content;
            return this;
        }

        public NotificationGetResponseBuilder createDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public NotificationGetResponseBuilder seenDateTime(LocalDateTime seenDateTime) {
            this.seenDateTime = seenDateTime;
            return this;
        }

        public NotificationGetResponse build() {
            return new NotificationGetResponse(id, senderLogin, receiverId, title, content, createDate, seenDateTime);
        }
    }
}
