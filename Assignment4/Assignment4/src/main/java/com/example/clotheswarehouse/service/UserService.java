package com.example.clotheswarehouse.service;

import com.example.clotheswarehouse.dto.UserRegistrationDto;
import com.example.clotheswarehouse.model.User;
import com.example.clotheswarehouse.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole(registrationDto.getRole());
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserRegistrationDto userDto) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(userDto.getUsername());
        if (!userDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        return userRepository.save(user);
    }
}