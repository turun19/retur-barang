package com.example.inventoryproject.service;

import com.example.inventoryproject.dto.RegisterDTO;
import com.example.inventoryproject.entity.ERole;
import com.example.inventoryproject.entity.Role;
import com.example.inventoryproject.entity.Users;
import com.example.inventoryproject.exception.ExistsByEmailException;
import com.example.inventoryproject.exception.ExistsByUsernameException;
import com.example.inventoryproject.repository.RoleRepository;
import com.example.inventoryproject.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class UserService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ValidateService validateService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    public void registerUser(RegisterDTO registerDTO){
        validateService.validate(registerDTO);

        if (usersRepository.existsByEmail(registerDTO.getEmail())){
            log.info("Already use email");
            throw new ExistsByEmailException("Email is Already!");
        }

        if(usersRepository.existsByUsername(registerDTO.getUsername())){
            log.info("Already Use Username");
            throw new ExistsByUsernameException("Username is Already");
        }

        Users users = new Users();
        users.setUsername(registerDTO.getUsername());
        users.setEmail(registerDTO.getEmail());
        users.setNumberPhone(registerDTO.getNumberPhone());
        users.setPassword(encoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role adminRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role is not found"));
        log.info("role is not found");
        roles.add(adminRole);
        users.setRoles(roles);

        usersRepository.save(users);
        log.info("success create account user!");
    }


}
