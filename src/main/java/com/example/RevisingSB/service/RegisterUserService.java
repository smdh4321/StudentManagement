package com.example.RevisingSB.service;

import com.example.RevisingSB.dto.RegisterUserDTO;
import com.example.RevisingSB.dto.RegisterUserResponseDTO;
import com.example.RevisingSB.entity.Users;
import com.example.RevisingSB.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserResponseDTO registerUser(RegisterUserDTO registerUserDTO){
        //check if user is already present
        if(usersRepository.findByUsername(registerUserDTO.getUsername()).isPresent()){
            throw new RuntimeException("User is already exists");
        }

        //encode the password
        Users users = new Users();
        users.setUsername(registerUserDTO.getUsername());
        users.setRole(registerUserDTO.getRole());
        users.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

        //save the user
        Users savedUser = usersRepository.save(users);
        RegisterUserResponseDTO registerUserResponseDTO = new RegisterUserResponseDTO(savedUser.getId(),savedUser.getUsername(),savedUser.getRole());
        return registerUserResponseDTO;
    }
}
