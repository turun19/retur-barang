package com.example.inventoryproject.exception;

import lombok.Data;

@Data
public class ExistsByEmailException extends RuntimeException {
    private final String message;

    public ExistsByEmailException(String message){
        super(message);
        this.message = message;
    }
}
