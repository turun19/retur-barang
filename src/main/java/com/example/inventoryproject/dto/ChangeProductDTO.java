package com.example.inventoryproject.dto;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class ChangeProductDTO {
    private Integer quantity;
}
