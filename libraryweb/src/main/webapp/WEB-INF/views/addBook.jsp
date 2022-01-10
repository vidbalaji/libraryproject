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
<h1>Add New Book</h1>
<form method="post" action="addBook">
	<table>
		<tr>
			<td>Name :</td>
			<td><input name="bookName"  type="text" /></td>
		</tr>
		<tr>
			<td>Author :</td>
			<td><input name="author"  type="text"  /></td>
		</tr>
			<tr>
			<td>Category :</td>
			<td><input name="category"  type="text"  /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="add Book" /></td>
		</tr>
		
	</table>
	
</form>
<a href="../welcome">  Welcome Page</a>
</html>