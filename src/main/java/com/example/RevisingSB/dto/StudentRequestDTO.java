package com.example.RevisingSB.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentRequestDTO {
    @NotBlank
    @Size(min =3 , max = 25, message = "Name field is required")
    private String name;
    @Email
    @NotBlank(message = "Email is required")
    private String email;
}
