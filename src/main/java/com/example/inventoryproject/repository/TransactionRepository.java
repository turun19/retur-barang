package com.example.inventoryproject.repository;

import com.example.inventoryproject.dto.TransactionDTO;
import com.example.inventoryproject.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findTopByProduct_IdAndReturnDateIsNullOrderByLoanDateDesc(Long productId);
}
