package com.example.clotheswarehouse.repository;

import com.example.clotheswarehouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}