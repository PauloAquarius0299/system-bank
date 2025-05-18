package com.paulotech.api_bank_tech.service;

import com.paulotech.api_bank_tech.dto.BankResponse;
import com.paulotech.api_bank_tech.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
}
