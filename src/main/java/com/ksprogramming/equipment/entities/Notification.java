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
    private User receiverId;
    private String title;
    private String content;
    private LocalDateTime createDateTime;
    private LocalDateTime seenDateTime;
    private LocalDateTime deleteDateTime;

    public Notification() {
    }

    public Notification(Long id, String senderLogin, User receiverId,
                        String title, String content, LocalDateTime createDateTime, LocalDateTime seenDateTime, LocalDateTime deleteDateTime) {
        this.id = id;
        this.senderLogin = senderLogin;
        this.receiverId = receiverId;
        this.title = title;
        this.content = content;
        this.createDateTime = createDateTime;
        this.seenDateTime = seenDateTime;
        this.deleteDateTime = deleteDateTime;
    }

    public Notification(String senderLogin, User receiverId, String title,
                        String content, LocalDateTime createDateTime, LocalDateTime seenDateTime, LocalDateTime deleteDateTime) {
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

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getSeenDateTime() {
        return seenDateTime;
    }

    public LocalDateTime getDeleteDateTime() {
        return deleteDateTime;
    }
}
