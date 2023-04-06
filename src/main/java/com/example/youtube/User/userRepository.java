package com.example.youtube.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface userRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String user_email);
}