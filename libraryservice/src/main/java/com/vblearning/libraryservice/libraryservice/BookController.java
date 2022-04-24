package com.vblearning.libraryservice.libraryservice;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class BookController {
	@Autowired
	private BookRepositary repo;
	@Autowired
	RabbitTemplate rabbitTemplate;

	@GetMapping("/books")
	public List<Book> listAll() {
		List<Book> listBooks = repo.findAll();
		// model.addAttribute("listUsers", listUsers);
		return listBooks;
	}

	@GetMapping("/books/category/{category}")
	public List<Book> listBookByCategory(@PathVariable("category") String category) {
		List<Book> listBooks = repo.findByCategory(category);

		return listBooks;
	}

	@PostMapping("/addBook")
	public ResponseEntity<Object> createBook(@RequestBody Book book) {
		Book savedBook = repo.save(book);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBook.getId())
				.toUri();
		String newBook = "New book added:" + savedBook.getBookName();
		byte[] byteMessage = newBook.getBytes();

		Message message = MessageBuilder.withBody(byteMessage).build();

		rabbitTemplate.send("fanoutex", "", message);
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
		Optional<Book> bookData = repo.findById(id);

		if (bookData.isPresent()) {
			Book _book = bookData.get();
			_book.setCategory(book.getCategory());
			_book.setAuthor(book.getAuthor());
			_book.setBookName(book.getBookName());
			repo.save(_book);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") int id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
