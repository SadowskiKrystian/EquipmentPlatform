package com.ksprogramming.equipment.data;

public class EmailData {
    private String to;
    private String subject;
    private String text;

    public EmailData(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public EmailData(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
