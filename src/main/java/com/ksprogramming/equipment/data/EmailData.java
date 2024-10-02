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

    public static EmailDataBuilder builder(){
        return new EmailDataBuilder();
    }

    public static class EmailDataBuilder {
        private String to;
        private String subject;
        private String text;

        public EmailDataBuilder setTo(String to) {
            this.to = to;
            return this;
        }

        public EmailDataBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public EmailDataBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public EmailData build() {
            return new EmailData(to, subject, text);
        }
    }
}
