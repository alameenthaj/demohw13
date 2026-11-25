package com.example.demohw13.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demohw13.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

}
