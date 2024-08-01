package com.example.inventoryproject.service;

import com.example.inventoryproject.dto.BorrowTransactionResponseDTO;
import com.example.inventoryproject.dto.ReturnTransactionResponseDTO;
import com.example.inventoryproject.dto.TransactionDTO;
import com.example.inventoryproject.entity.Product;
import com.example.inventoryproject.entity.Transaction;
import com.example.inventoryproject.entity.Users;
import com.example.inventoryproject.mapper.TransactionMapper;
import com.example.inventoryproject.repository.ProductRepository;
import com.example.inventoryproject.repository.TransactionRepository;
import com.example.inventoryproject.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Slf4j
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private TransactionMapper transactionMapper;

    public BorrowTransactionResponseDTO borrowProduct(TransactionDTO transactionDTO) {
        Product product = productRepository.findById(transactionDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Users user = userRepository.findById(transactionDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (product.getQuantity() < transactionDTO.getQuantity()) {
            throw new RuntimeException("Insufficient quantity");
        }

        product.setQuantity(product.getQuantity() - transactionDTO.getQuantity());
        productRepository.save(product);

        Transaction transaction = transactionMapper.toEntity(transactionDTO, product, user);
        transaction.setLoanDate(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(transaction);
        log.info("Transaction saved: {}", savedTransaction);
        return transactionMapper.toBorrowResponseDTO(savedTransaction);
    }

    @Transactional
    public ReturnTransactionResponseDTO returnProduct(Long productId) {
        log.info("Attempting to return product with ID: {}", productId);

        Transaction transaction = transactionRepository.findTopByProduct_IdAndReturnDateIsNullOrderByLoanDateDesc(productId)
                .orElseThrow(() -> {
                    log.error("No active loan found for product with ID: {}", productId);
                    return new RuntimeException("No active loan found for this product");
                });

        log.info("Found active transaction: {}", transaction);

        Product product = transaction.getProduct();
        int updatedQuantity = product.getQuantity() + transaction.getQuantity();
        product.setQuantity(updatedQuantity);

        productRepository.save(product);
        log.info("Updated product quantity. New quantity: {}", updatedQuantity);

        transaction.setReturnDate(LocalDateTime.now());
        Transaction updatedTransaction = transactionRepository.save(transaction);
        log.info("Transaction updated with return date: {}", updatedTransaction);

        return transactionMapper.toReturnResponseDTO(updatedTransaction);
    }
}
