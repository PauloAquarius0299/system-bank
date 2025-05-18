package com.paulotech.api_bank_tech.service;

import com.paulotech.api_bank_tech.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
