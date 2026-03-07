package com.ecommerce.shopplus.service;

import com.ecommerce.shopplus.model.User;
import com.ecommerce.shopplus.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String email, String password, String fullName) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User(email, passwordEncoder.encode(password), fullName);
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
