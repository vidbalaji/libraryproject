<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<style>
table, th, td {
  border: 1px solid white;
  border-collapse: collapse;
}
th, td {
  background-color: #96D4D4;
}
</style>
<body>

	<h2>List of Books</h2>

	<table>
		<tr>
			<th>Name</th>
			<th>Author</th>
			<th>Category</th>
		</tr>
		<c:forEach var="book" items="${bookList}">

			<tr>
				<td>${book.bookName}</td>


				<td>${book.author}</td>


				<td>${book.category}</td>
				<td><a href="../updateBook?id=${book.id}"> Update </a></td>
				<td><a href="../deleteBook?id=${book.id}"> Delete </a></td>

			</tr>


		</c:forEach>

	</table>
	<a href="../welcome"> Welcome Page</a>
</body>
</html>