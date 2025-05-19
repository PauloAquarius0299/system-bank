package com.paulotech.api_bank_tech.service;

import com.paulotech.api_bank_tech.dto.TransactionDto;
import com.paulotech.api_bank_tech.entity.Transaction;

public interface TransactionService {
    void saveTransaction(TransactionDto transactionDto);
}
