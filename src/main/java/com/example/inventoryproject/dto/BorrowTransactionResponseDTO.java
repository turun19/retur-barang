package com.example.inventoryproject.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowTransactionResponseDTO {
    private Long userId;
    private Long id;
    private Long productId;
    private Integer quantity;
    private LocalDateTime loanDate;
}
