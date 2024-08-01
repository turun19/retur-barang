package com.example.inventoryproject.service;

import com.example.inventoryproject.dto.ChangeProductDTO;
import com.example.inventoryproject.dto.ProductDTO;
import com.example.inventoryproject.dto.ProductResponseDTO;
import com.example.inventoryproject.entity.Product;
import com.example.inventoryproject.mapper.ProductChangeMapper;
import com.example.inventoryproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductChangeMapper productChangeMapper;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public ProductResponseDTO addProduct(ProductDTO productDTO) {
        Product product = productChangeMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productChangeMapper.toResponseDTO(savedProduct);
    }

    public ProductResponseDTO updateProductQuantity(Long productId, ChangeProductDTO changeProductDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setQuantity(product.getQuantity() + changeProductDTO.getQuantity());
        Product updatedProduct = productRepository.save(product);
        return productChangeMapper.toResponseDTO(updatedProduct);
    }
}
