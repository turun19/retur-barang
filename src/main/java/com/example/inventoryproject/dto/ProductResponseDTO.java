package com.example.inventoryproject.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private Integer quantity;
    private String type;
    private LocalDateTime createdAt;
}
