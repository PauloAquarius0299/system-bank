package com.paulotech.api_bank_tech.repository;

import com.paulotech.api_bank_tech.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
