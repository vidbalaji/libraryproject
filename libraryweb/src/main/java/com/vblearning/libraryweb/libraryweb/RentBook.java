package com.vblearning.libraryweb.libraryweb;

public class RentBook {
	private Integer rentBookId;

	public Integer getRentBookId() {
		return rentBookId;
	}

	public void setRentBookId(Integer rentBookId) {
		this.rentBookId = rentBookId;
	}

	private java.sql.Date issueDate;

	private java.sql.Date dueDate;
	private java.sql.Date returnDate;
	private Book book;
	private User user;

	public java.sql.Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(java.sql.Date issueDate) {
		this.issueDate = issueDate;
	}

	public java.sql.Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(java.sql.Date dueDate) {
		this.dueDate = dueDate;
	}

	public java.sql.Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(java.sql.Date returnDate) {
		this.returnDate = returnDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}