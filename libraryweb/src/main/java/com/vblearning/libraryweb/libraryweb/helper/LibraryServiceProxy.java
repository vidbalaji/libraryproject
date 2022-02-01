package com.vblearning.libraryweb.libraryweb.helper;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vblearning.libraryweb.libraryweb.aspect.TrackCustomFlow;
import com.vblearning.libraryweb.libraryweb.model.Book;
import com.vblearning.libraryweb.libraryweb.model.RentBook;
import com.vblearning.libraryweb.libraryweb.model.User;

@FeignClient(name = "libraryservice")
public interface LibraryServiceProxy {
	@GetMapping("/books")
	public List<Book> listAll();

	@PostMapping("/addBook")
	public void createBook(Book book);

	@PutMapping("/books/{id}")
	public ResponseEntity<HttpStatus> updateBook(@PathVariable("id") int id, Book book);

	@DeleteMapping("/books/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") int id);

	@TrackCustomFlow
	@PostMapping("/addUser")
	public ResponseEntity<Object> createUser(@RequestBody User user);

	@GetMapping("/users")
	public List<User> listAllUsers();

	@GetMapping("/books/category/{category}")
	public List<Book> listBookByCategory(@PathVariable("category") String category);

	@PostMapping("/rentBook")
	public ResponseEntity<Object> rentBook(@RequestBody RentBook rentBook);

	@GetMapping("/rentBookAvailable")
	public List<Book> listAllRentBooksAvailable();

	@GetMapping("/rentBook")
	public List<RentBook> listAllRentBooks();

	@DeleteMapping("/rentBook/{id}")
	public ResponseEntity<HttpStatus> deleteRentBook(@PathVariable("id") int id);

}