package com.example.inventoryproject.controller;

import com.example.inventoryproject.dto.RegisterDTO;
import com.example.inventoryproject.exception.ExistsByEmailException;
import com.example.inventoryproject.exception.ExistsByUsernameException;
import com.example.inventoryproject.response.MessageResponse;
import com.example.inventoryproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    @SecurityRequirements
    @Operation(summary = "User register for create account")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterDTO registerDTO) {
        try {
            userService.registerUser(registerDTO);
            return ResponseEntity.ok(new MessageResponse(false, "User Registered Successful"));
        }catch (ExistsByEmailException e){
            return ResponseEntity.badRequest().body(new MessageResponse(true, e.getMessage()));
        }catch (ExistsByUsernameException e){
            return ResponseEntity.badRequest().body(new MessageResponse(true, e.getMessage()));
        }
    }
}
