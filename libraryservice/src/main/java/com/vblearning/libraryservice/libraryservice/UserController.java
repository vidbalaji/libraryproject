package com.vblearning.libraryservice.libraryservice;

import java.net.URI;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	@Autowired
	private UserRepositary repo;

	@GetMapping("/users")
	public List<User> listAllUsers() {
		List<User> listUsers = repo.findAll();

		return listUsers;
	}

	@PostMapping("/addUser")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		user.setPassword("1234");
		User savedUser = repo.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@GetMapping("/users/rentbooks/{id}")
	public Set<RentBook> listBooksByUserId(@PathVariable("id") int id) {
		Optional<User> userData = repo.findById(id);

		Set<RentBook> rb = new LinkedHashSet<>();
		if (userData.isPresent()) {
			rb = userData.get().getUserRentBookList();
		}
		return rb;
	}

}
