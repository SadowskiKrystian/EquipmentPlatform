package com.ksprogramming.equipment.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String senderLogin;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    private String title;
    private String content;
    @Column(name = "create_datetime")
    private LocalDateTime createDateTime;
    @Column(name = "seen_datetime")
    private LocalDateTime seenDateTime;
    @Column(name = "delete_datetime")
    private LocalDateTime deleteDateTime;

    public Notification() {
    }

    public Notification(Long id, String senderLogin, User receiverId,
                        String title, String content, LocalDateTime createDateTime, LocalDateTime seenDateTime, LocalDateTime deleteDateTime) {
        this.id = id;
        this.senderLogin = senderLogin;
        this.receiver = receiverId;
        this.title = title;
        this.content = content;
        this.createDateTime = createDateTime;
        this.seenDateTime = seenDateTime;
        this.deleteDateTime = deleteDateTime;
    }

    public Notification(String senderLogin, User receiverId, String title,
                        String content, LocalDateTime createDateTime, LocalDateTime seenDateTime, LocalDateTime deleteDateTime) {
        this.senderLogin = senderLogin;
        this.receiver = receiverId;
        this.title = title;
        this.content = content;
        this.createDateTime = createDateTime;
        this.seenDateTime = seenDateTime;
        this.deleteDateTime = deleteDateTime;
    }

    public Long getId() {
        return id;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getSeenDateTime() {
        return seenDateTime;
    }

    public LocalDateTime getDeleteDateTime() {
        return deleteDateTime;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public void setSeenDateTime(LocalDateTime seenDateTime) {
        this.seenDateTime = seenDateTime;
    }

    public void setDeleteDateTime(LocalDateTime deleteDateTime) {
        this.deleteDateTime = deleteDateTime;
    }

    public static NotificationBuilder builder(){
        return new NotificationBuilder();
    }

    public static class NotificationBuilder {
        private Long id;
        private String senderLogin;
        private User receiver;
        private String title;
        private String content;
        private LocalDateTime createDateTime;
        private LocalDateTime seenDateTime;
        private LocalDateTime deleteDateTime;

        public NotificationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public NotificationBuilder senderLogin(String senderLogin) {
            this.senderLogin = senderLogin;
            return this;
        }

        public NotificationBuilder receiver(User receiver) {
            this.receiver = receiver;
            return this;
        }

        public NotificationBuilder title(String title) {
            this.title = title;
            return this;
        }

        public NotificationBuilder content(String content) {
            this.content = content;
            return this;
        }

        public NotificationBuilder createDateTime(LocalDateTime createDateTime) {
            this.createDateTime = createDateTime;
            return this;
        }

        public NotificationBuilder seenDateTime(LocalDateTime seenDateTime) {
            this.seenDateTime = seenDateTime;
            return this;
        }

        public NotificationBuilder deleteDateTime(LocalDateTime deleteDateTime) {
            this.deleteDateTime = deleteDateTime;
            return this;
        }

        public Notification build() {
            return new Notification(id, senderLogin, receiver, title, content, createDateTime, seenDateTime, deleteDateTime);
        }
    }
}
