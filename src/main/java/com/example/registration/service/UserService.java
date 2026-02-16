package com.example.registration.service;

import com.example.registration.model.User;
import com.example.registration.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String fullName, String email, String rawPassword) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already registered!");
        }

        String hashed = passwordEncoder.encode(rawPassword);

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(hashed);
        user.setRole("USER");

        userRepository.save(user);
    }
}
