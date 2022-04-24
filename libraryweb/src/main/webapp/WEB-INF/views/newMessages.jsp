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
		<a href="../listBook"> List/Modify Book</a> <br> <a
			href="../addBook"> Add Book</a> <br> <a href="../addUser">
			Add User</a> <br> <a href="../returnBook">Return Book</a> <br>
		<a href="../rentBook">Rent Book</a> <br> <a
			href="../listBookByCategory">List Book By Category</a> <br>
			<h3>${newMessages}</h3><br>
</body>
</html>