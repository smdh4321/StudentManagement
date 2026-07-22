package com.example.RevisingSB.controller;

import com.example.RevisingSB.dto.RegisterUserDTO;
import com.example.RevisingSB.dto.RegisterUserResponseDTO;
import com.example.RevisingSB.entity.Users;
import com.example.RevisingSB.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RegisterUserController {
    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDTO> registerUser(@RequestBody RegisterUserDTO registerUserDTO){
        registerUserDTO.setRole("ROLE_USER");
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUserService.registerUser(registerUserDTO));
    }

    @PostMapping("/register-admin")
    public ResponseEntity<RegisterUserResponseDTO> registerAdmin(@RequestBody RegisterUserDTO registerUserDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUserService.registerUser(registerUserDTO));
    }

}
