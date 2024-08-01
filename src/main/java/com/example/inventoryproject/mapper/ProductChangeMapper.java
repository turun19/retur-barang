package com.example.inventoryproject.mapper;

import com.example.inventoryproject.dto.ProductDTO;
import com.example.inventoryproject.dto.ProductResponseDTO;
import com.example.inventoryproject.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductChangeMapper {
    public ProductResponseDTO toResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setQuantity(product.getQuantity());
        dto.setType(product.getType());
        dto.setCreatedAt(product.getCreatedAt());
        return dto;
    }

    public Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setQuantity(dto.getQuantity());
        product.setType(dto.getType());
        return product;
    }
}
