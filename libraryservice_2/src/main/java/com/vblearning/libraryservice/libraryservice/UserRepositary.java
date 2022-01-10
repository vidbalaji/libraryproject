package com.vblearning.libraryservice.libraryservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary extends JpaRepository<User, Integer> {

}