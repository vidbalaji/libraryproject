package com.vblearning.libraryweb.libraryweb;

import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	List<Book> bookList;
	@Autowired
	LibraryHelper libraryHelper;

	@PostConstruct
	public void postConstructMethod() {
		logger.info("getting booklist ");
		bookList = libraryHelper.listAll();
	}

	@GetMapping({ "/", "/welcome" })
	public String welcome(Model m) {
		return "welcome";
	}

	@GetMapping({ "/listBook" })
	public String listBooks(Model m) {
		bookList = libraryHelper.listAll();
		m.addAttribute(bookList);
		return "listBook";
	}

	@GetMapping({ "/listBookByCategory" })
	public String listBookByCategory(Model m) {
		bookList = libraryHelper.listAll();
		m.addAttribute(bookList);
		List<String> categoryList = libraryHelper.getCategories();
		logger.info("categoryList" + categoryList.get(0));
		m.addAttribute("categoryList", categoryList);
		return "listBookByCategory";
	}

	@RequestMapping(value = "/listBookByCategory", method = RequestMethod.POST, params = { "categoryName2" })
	public String listBookByCategoryPost(@RequestParam(value = "categoryName2") String category, Model m) {
		List<String> categoryList = libraryHelper.getCategories();
		logger.info("category" + category);
		m.addAttribute("categoryList", categoryList);
		if ((category.equals("null") || (category != null && category.trim().isEmpty()))) {
			m.addAttribute(bookList);
			logger.info("category inside" + category);
			m.addAttribute("categoryList", categoryList);

		} else {
			List<Book> bookListByCategory = libraryHelper.listBookByCategory(category);
			m.addAttribute(bookListByCategory);
		}

		return "/listBookByCategory";
	}

	@GetMapping({ "/addBook" })
	public String addBookView() {

		return "addBook";
	}

	@GetMapping({ "/addUser" })
	public String addUserView() {

		return "addUser";
	}

	@PostMapping({ "/addBook" })
	public String addBook(@ModelAttribute("book") Book book) {
		libraryHelper.createBook(book);
		return "redirect:/listBook";
	}

	@PostMapping({ "/addUser" })
	public String addUser(@ModelAttribute("user") User user) {
		libraryHelper.createUser(user);
		return "redirect:/listBook";

	}

	@GetMapping({ "/returnBook" })
	public String returnBook(Model m) {
		List<RentBook> avlBookList = libraryHelper.listAllRentBooks();
		m.addAttribute("myRentBookList", avlBookList);
		m.addAttribute("fine", 0);
		return "returnBook";
	}

	@RequestMapping(value = "/returnBook", method = RequestMethod.POST)
	public String returnBookPost(@RequestParam(value = "rentBookId") int rentBookId,
			@RequestParam(value = "buttonName") String buttonName, Model m) {
		logger.info("Inside  returnBookPost buttonName:" + buttonName);

		RentBook rb = libraryHelper.findRentBookById(rentBookId);
		Date dueDate = rb.getDueDate();
		Date today = new java.sql.Date(new java.util.Date().getTime());
		logger.info("Issuedate/bookName2:" + rb.getIssueDate() + "/" + rb.getBook().getBookName());

		if (buttonName.equals("yes") == false && dueDate.before(today)) {
			int days = today.compareTo(dueDate);
			float fine = days * 5;
			logger.info("days" + days);
			List<RentBook> avlBookList = libraryHelper.listAllRentBooks();
			m.addAttribute("myRentBookList", avlBookList);
			m.addAttribute("fine", fine);

			return "returnBook";
		}

		ResponseEntity<HttpStatus> httpStat = libraryHelper.deleteRentBook(rentBookId);
		logger.info("Inside " + httpStat.getStatusCodeValue());
		logger.info("Inside " + httpStat.toString());
		return "redirect:/listBook";
	}

	@GetMapping({ "/rentBook" })
	public String rentBook(Model m) {
		List<Book> avlBookList = libraryHelper.listAllRentBooksAvailable();
		List<User> userList = libraryHelper.listAllUsers();
		m.addAttribute("myUserList", userList);
		m.addAttribute("myBookList", avlBookList);
		return "rentBook";
	}

	@RequestMapping(value = "/rentBook", method = RequestMethod.POST)
	public String rentBookPost(@RequestParam(value = "bookId") int bookId, @RequestParam(value = "userId") int userId,
			@RequestParam(value = "issueDate") Date issueDate, @RequestParam(value = "dueDate") Date dueDate) {
		User user = libraryHelper.findUserById(userId);
		Book book = libraryHelper.findBookById(bookId);
		libraryHelper.rentBookService(user, book, issueDate, dueDate);
		logger.info("user/bookName2:" + user.getUser() + "/" + book.getBookName());
		return "redirect:/listBook";

	}

	@GetMapping({ "/addCategory" })
	public String addCategory() {

		return "addCategory";
	}

	@RequestMapping(value = "/updateBook", method = RequestMethod.GET, params = { "id" })
	public String updateBook(@RequestParam(value = "id") int id, Model m) {
		Book book = libraryHelper.findBookById(id);
		m.addAttribute(book);
		return "updateBook";
	}

	@RequestMapping(value = "/updateBook", method = RequestMethod.POST, params = { "id" })
	public String updateBookPost(@RequestParam(value = "id") int id, @ModelAttribute("book") Book book) {
		libraryHelper.updateBook(book);
		return "redirect:/listBook";
	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET, params = { "id" })
	public String deleteBook(@RequestParam(value = "id") int id) {
		libraryHelper.deleteBook(id);
		return "redirect:/listBook";
	}

}
