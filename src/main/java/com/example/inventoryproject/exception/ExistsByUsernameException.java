package com.example.inventoryproject.exception;

import lombok.Data;

@Data
public class ExistsByUsernameException extends RuntimeException {
    private final String message;

    public ExistsByUsernameException(String message){
        super(message);
        this.message = message;
    }
}
