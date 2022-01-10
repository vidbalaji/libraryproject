package com.vblearning.libraryservice.libraryservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentBookRepositary extends JpaRepository<RentBook, Integer> {

}