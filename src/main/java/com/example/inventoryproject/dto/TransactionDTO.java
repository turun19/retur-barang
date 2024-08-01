package com.example.inventoryproject.dto;

import lombok.Data;

@Data
public class TransactionDTO {
    private Long userId;
    private Long productId;
    private Integer quantity;
}
