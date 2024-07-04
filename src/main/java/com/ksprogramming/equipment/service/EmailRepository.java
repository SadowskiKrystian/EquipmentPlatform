package com.ksprogramming.equipment.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

@Repository
public class EmailRepository {
        private JavaMailSender mailSender;

    public EmailRepository(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void sendEmail(SimpleMailMessage message) {
        mailSender.send(message);
    }
}
