package com.paulotech.api_bank_tech.service.impl;

import com.paulotech.api_bank_tech.dto.TransactionDto;
import com.paulotech.api_bank_tech.entity.Transaction;
import com.paulotech.api_bank_tech.repository.TransactionRepository;
import com.paulotech.api_bank_tech.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                .transactionType(transactionDto.getTransactionType())
                .accountNumber(transactionDto.getAccountNumber())
                .amount(transactionDto.getAmount())
                .status("SUCCESS")
                .build();
        transactionRepository.save(transaction);
        System.out.println("Transação efetivada com sucesso!");
    }
}
