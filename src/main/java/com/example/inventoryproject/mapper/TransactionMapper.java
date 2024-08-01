package com.example.inventoryproject.mapper;

import com.example.inventoryproject.dto.BorrowTransactionResponseDTO;
import com.example.inventoryproject.dto.ReturnTransactionResponseDTO;
import com.example.inventoryproject.dto.TransactionDTO;
import com.example.inventoryproject.entity.Product;
import com.example.inventoryproject.entity.Transaction;
import com.example.inventoryproject.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public BorrowTransactionResponseDTO toBorrowResponseDTO(Transaction transaction) {
        BorrowTransactionResponseDTO dto = new BorrowTransactionResponseDTO();
        dto.setUserId(transaction.getUser().getId());
        dto.setId(transaction.getId());
        dto.setProductId(transaction.getProduct().getId());
        dto.setQuantity(transaction.getQuantity());
        dto.setLoanDate(transaction.getLoanDate());
        return dto;
    }

    public ReturnTransactionResponseDTO toReturnResponseDTO(Transaction transaction) {
        ReturnTransactionResponseDTO dto = new ReturnTransactionResponseDTO();
        dto.setUserId(transaction.getUser().getId());
        dto.setId(transaction.getId());
        dto.setProductId(transaction.getProduct().getId());
        dto.setQuantity(transaction.getQuantity());
        dto.setLoanDate(transaction.getLoanDate());
        dto.setReturnDate(transaction.getReturnDate());
        return dto;
    }

    public Transaction toEntity(TransactionDTO dto, Product product, Users user) {
        Transaction transaction = new Transaction();
        transaction.setQuantity(dto.getQuantity());
        transaction.setProduct(product);
        transaction.setUser(user);
        return transaction;
    }
}
