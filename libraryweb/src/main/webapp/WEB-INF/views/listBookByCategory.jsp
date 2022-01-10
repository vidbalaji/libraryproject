<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	
	<form method="POST"
		action="/listBookByCategory?categoryName=${categoryName2}">
		<table>
			<tr>
				<th>Select Category</th>
			</tr>
			<tr>

				<td><select name="categoryName2" id="selectCategory">
						<option value=null>List all Books</option>
						<c:forEach var="category" items="${categoryList}">

							<c:choose>

								<c:when test="${categoryName2 == category}">
									<option value="${category}" selected>${category}</option>
								</c:when>

								<c:otherwise>
									<option value="${category}">${category}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>
				<td></td>
				<td><input type="submit" value="Ok" /></td>
			</tr>
		</table>
	</form>
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