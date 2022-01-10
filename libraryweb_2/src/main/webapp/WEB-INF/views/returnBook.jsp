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
<h1>Return Book</h1>
<form method="post" action="returnBook">
	<table>
		<tr>
			<td>Book Name :</td>
			<td><select name="rentBookId" id="selectBook">
					<c:forEach var="rentBook" items="${myRentBookList}">
						<option value="${rentBook.rentBookId}">${rentBook.book.bookName}</option>
					</c:forEach>
			</select></td>
		</tr>

		<tr>
			<td></td>
			<td><input type="submit" value="Return Book" /></td>
		</tr>
		<tr>
			<td><INPUT type="hidden" NAME="buttonName" value="no"
				id="buttonName"></td>
		</tr>
		<tr>
			<c:if test="${fine >0.0}">

				<td><label id="collectFine">Collect Fine : ${fine}</label> <INPUT
					TYPE="BUTTON" VALUE="Collect fine" ONCLICK="button1()"></td>
			</c:if>

		</tr>
	</table>

</form>
<SCRIPT>
	function button1() {
		document.getElementById('buttonName').value = "yes";
		alert('Fine Collected. Please click Return Book ')
	}
</SCRIPT>
<a href="../welcome"> Welcome Page</a>
</html>