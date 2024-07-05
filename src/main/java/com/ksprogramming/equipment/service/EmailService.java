package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.EmailData;
import com.ksprogramming.equipment.data.TokenData;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailServiceInterface{
    private EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public void sendEmailMessage(EmailData email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        emailRepository.sendEmail(message);
    }

    public void sendEmailVerificationMessage(EmailData email, TokenData token){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setSubject("Equipment-platform");
        String text = "Email verification \n";
        text += "Dear " + email.getTo() + ",\n";
        text += "Please click the link below to verify your email:\n";
        text += "http://localhost:8081/verify?token=" + token.getValue() + "\n";
        text += "Thank you,\nEquipment-platform";
        message.setText(text);
        emailRepository.sendEmail(message);
    }
}
