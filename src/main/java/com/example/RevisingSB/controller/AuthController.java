package com.example.RevisingSB.controller;

import com.example.RevisingSB.dto.AuthRequestDTO;
import com.example.RevisingSB.dto.AuthResponseDTO;
import com.example.RevisingSB.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;
    @PostMapping("/authenticate")
    public AuthResponseDTO generateToken(@RequestBody AuthRequestDTO authRequestDTO){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(),authRequestDTO.getPassword())
            );

            return jwtUtil.generateToken(authRequestDTO.getUsername());
        }catch (Exception e){
            throw e;
        }
    }
}
