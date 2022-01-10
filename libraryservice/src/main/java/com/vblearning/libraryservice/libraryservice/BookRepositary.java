package com.vblearning.libraryservice.libraryservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositary extends JpaRepository<Book, Integer> {
	public List<Book> findByCategory(String category);

}