package com.vblearning.libraryservice.libraryservice;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class RentBookController {
	@Autowired
	private RentBookRepositary repo;
	@Autowired
	private BookRepositary bookRepo;

	@PostMapping("/rentBook")
	public ResponseEntity<Object> createBook(@RequestBody RentBook rentBook) {
		RentBook savedBook = repo.save(rentBook);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedBook.getRentBookId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@GetMapping("/rentBook")
	public List<RentBook> listAllRentBooks() {
		List<RentBook> listRentBooks = repo.findAll();
		// model.addAttribute("listUsers", listUsers);
		return listRentBooks;
	}

	@GetMapping("/rentBookAvailable")
	public List<Book> listAllRentBooksAvailable() {
		List<Book> listRentBooksAvl = bookRepo.findAll();

		List<RentBook> listRentBooks = listAllRentBooks();
		for (int i = 0; i < listRentBooks.size(); i++) {
			RentBook rb = listRentBooks.get(i);

			Book localBook = bookRepo.getById(rb.getBook().getId());
			if (localBook != null && listRentBooksAvl.contains(localBook)) {
				listRentBooksAvl.remove(localBook);
			}
		}
		return listRentBooksAvl;
	}

	@DeleteMapping("/rentBook/{id}")
	public ResponseEntity<HttpStatus> deleteRentBook(@PathVariable("id") int id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
