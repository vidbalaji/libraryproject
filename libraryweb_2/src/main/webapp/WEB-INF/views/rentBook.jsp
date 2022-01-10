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
<h1>Rent Book</h1>
<form method="post" action="rentBook">
	<table>

		<tr>
			<td>Book Name :</td>
			<td><select name="bookId" id="selectBook">
					<c:forEach var="book" items="${myBookList}">
						<option value="${book.id}">${book.bookName}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>User Name :</td>
			<td><select name="userId" id="selectUser">
					<c:forEach var="user2" items="${myUserList}">
						<option value="${user2.id}">${user2.user}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>Issue Date :</td>
			<td><input name="issueDate" type="date" id="issueDate"/></td>
		</tr>
		<tr>
			<td>Due Date :</td>
			<td><input name="dueDate" type="date" id="dueDate"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="rent book" /></td>
		</tr>
	</table>

</form>
<script>
document.getElementById('issueDate').value =  new Date().toISOString().substring(0, 10);
const d = new Date();
const tomorrow = new Date();
tomorrow.setDate(d.getDate() + 7);

document.getElementById('dueDate').value =  tomorrow.toISOString().substring(0, 10);
</script>
<a href="../welcome"> Welcome Page</a>
</html>