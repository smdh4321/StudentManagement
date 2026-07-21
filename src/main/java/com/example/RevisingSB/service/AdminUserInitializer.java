package com.example.RevisingSB.service;

import com.example.RevisingSB.entity.Users;
import com.example.RevisingSB.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {

    //This file is responsible for creating
    // default Admin User and the normal user when the data in DB is not present
    @Bean
    public CommandLineRunner createAdminUser(UsersRepository usersRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(usersRepository.findByUsername("admin").isEmpty()){
                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ROLE_ADMIN");
                usersRepository.save(admin);
                System.out.println("Default admin user created");
            }
            if(usersRepository.findByUsername("user").isEmpty()){
                Users admin = new Users();
                admin.setUsername("user");
                admin.setPassword(passwordEncoder.encode("user123"));
                admin.setRole("ROLE_USER");
                usersRepository.save(admin);
                System.out.println("Default user created");
            }
        };
    }

}
