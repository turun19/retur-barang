package com.example.inventoryproject.controller;

import com.example.inventoryproject.dto.*;
import com.example.inventoryproject.entity.Transaction;
import com.example.inventoryproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/borrow")
    public ResponseEntity<BaseResponse<BorrowTransactionResponseDTO>> borrowProduct(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(BaseResponse.success(transactionService.borrowProduct(transactionDTO), "Berhasil meminjam barang"));

    }
    @PostMapping("/return/{productId}")
    public ResponseEntity<BaseResponse<ReturnTransactionResponseDTO>> returnProduct (@PathVariable Long productId){
        return ResponseEntity.ok(BaseResponse.success(transactionService.returnProduct(productId), "Berhasil mengembalikan barang"));
    }
}
