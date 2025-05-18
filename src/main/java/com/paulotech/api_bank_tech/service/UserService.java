package com.paulotech.api_bank_tech.service;

import com.paulotech.api_bank_tech.dto.BankResponse;
import com.paulotech.api_bank_tech.dto.CreditDebitRequest;
import com.paulotech.api_bank_tech.dto.EnquiryRequest;
import com.paulotech.api_bank_tech.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
    BankResponse balanceEnquiry(EnquiryRequest request);
    String nameEnquiry(EnquiryRequest request);
    BankResponse creditAccount(CreditDebitRequest request);
    BankResponse debitAccount(CreditDebitRequest request);
}
