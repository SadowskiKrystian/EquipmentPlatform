package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.EmailData;
import com.ksprogramming.equipment.data.TokenData;

public interface EmailServiceInterface {
    void sendEmailMessage(EmailData email);
    void sendEmailVerificationMessage(EmailData email, TokenData token);
}
