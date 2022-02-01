package com.vblearning.libraryweb.libraryweb.helper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.vblearning.libraryweb.libraryweb.aspect.TrackCustomFlow;
import com.vblearning.libraryweb.libraryweb.model.Book;
import com.vblearning.libraryweb.libraryweb.model.RentBook;
import com.vblearning.libraryweb.libraryweb.model.User;

@Service
public class LibraryHelper {
	List<Book> bookList;
	List<RentBook> rentBookList;
	List<User> userList;
	@Autowired
	LibraryServiceProxy libraryServiceProxy;

	public List<Book> listAll() {
		// TODO Auto-generated method stub
		bookList = libraryServiceProxy.listAll();
		userList = libraryServiceProxy.listAllUsers();
		return bookList;
	}

	public void createBook(Book book) {
		// TODO Auto-generated method stub
		libraryServiceProxy.createBook(book);
	}

	public Book findBookById(int id) {
		for (int i = 0; i < bookList.size(); i++) {
			Book book = bookList.get(i);
			if (id == book.getId())
				return book;
		}
		return null;
	}

	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		libraryServiceProxy.updateBook(book.getId(), book);
	}

	public void deleteBook(int id) {
		// TODO Auto-generated method stub
		libraryServiceProxy.deleteBook(id);
	}

	@TrackCustomFlow
	public void createUser(User user) {
		// TODO Auto-generated method stub
		libraryServiceProxy.createUser(user);
	}

	public List<User> listAllUsers() {
		// TODO Auto-generated method stub
		userList = libraryServiceProxy.listAllUsers();
		return userList;
	}

	public List<String> getCategories() {
		List<String> categoryList = new ArrayList<String>();
		;
		// TODO Auto-generated method stub
		for (int i = 0; i < bookList.size(); i++) {
			Book b = bookList.get(i);
			if (categoryList.contains(b.getCategory()) == false) {
				categoryList.add(b.getCategory());
			}
		}
		return categoryList;
	}

	public List<Book> listBookByCategory(String category) {
		// TODO Auto-generated method stub
		List<Book> bookListByCategory = libraryServiceProxy.listBookByCategory(category);

		return bookListByCategory;

	}

	public List<Book> listAllRentBooksAvailable() {
		// TODO Auto-generated method stub
		List<Book> bookListByAvl = libraryServiceProxy.listAllRentBooksAvailable();

		return bookListByAvl;

	}

	public User findUserById(int userId) {
		// TODO Auto-generated method stub
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			if (userId == user.getId())
				return user;
		}

		return null;
	}

	public void rentBookService(User user, Book book, Date issueDate, Date dueDate) {
		// TODO Auto-generated method stub
		RentBook rb = new RentBook();
		rb.setBook(book);
		rb.setUser(user);
		rb.setDueDate(dueDate);
		rb.setIssueDate(issueDate);
		libraryServiceProxy.rentBook(rb);
	}

	public List<RentBook> listAllRentBooks() {
		// TODO Auto-generated method stub
		rentBookList = libraryServiceProxy.listAllRentBooks();
		return rentBookList;
	}

	public RentBook findRentBookById(int rentBookId) {
		// TODO Auto-generated method stub
		if (rentBookList == null)
			listAllRentBooks();
		for (int i = 0; i < rentBookList.size(); i++) {
			RentBook rb = rentBookList.get(i);
			if (rentBookId == rb.getRentBookId())
				return rb;
		}
		return null;
	}

	public ResponseEntity<HttpStatus> deleteRentBook(@PathVariable("id") int id) {
		ResponseEntity<HttpStatus> httpStat = libraryServiceProxy.deleteRentBook(id);
		return httpStat;
	}

}
