package com.example.inventoryproject.controller;

import com.example.inventoryproject.dto.BaseResponse;
import com.example.inventoryproject.dto.ChangeProductDTO;
import com.example.inventoryproject.dto.ProductDTO;
import com.example.inventoryproject.dto.ProductResponseDTO;
import com.example.inventoryproject.entity.Product;
import com.example.inventoryproject.response.MessageResponse;
import com.example.inventoryproject.service.ProductService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/get")
    public ResponseEntity<?> findALl(){
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<ProductResponseDTO>> addProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(BaseResponse.success(productService.addProduct(productDTO),"Berhasil menambahkan barang"));
    }

    @PutMapping("/{id}/quantity")
    public ResponseEntity<BaseResponse<ProductResponseDTO>> updateProductQuantity(@PathVariable Long id, @RequestBody ChangeProductDTO changeProductDTO) {
        return ResponseEntity.ok(BaseResponse.success(productService.updateProductQuantity(id, changeProductDTO),"Berhasil menambahkan barang"));
    }
}
