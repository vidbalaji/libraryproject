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
<script type="text/javascript">
	//function to check validation (Required field)
	function checkReqFields() {
		var returnValue;
		var name = document.getElementById("txtName").value;

		returnValue = true;
		if (name.trim() == "") {
			document.getElementById("reqTxtName").innerHTML = "* Name is required.";
			alert('Given data is incorrect');
			returnValue = false;
		}

		return returnValue;
	}
</script>
<h1>Add New User</h1>
<form onsubmit="return checkReqFields()" action="addUser" method="post">
	<table>
		<tr>
			<td>Name :</td>
			<td><input name="User" type="text" id="txtName"> <span
				id="reqTxtName" class="reqError"></span></td>
		</tr>
		<tr>
			<td>Gender :</td>
			<td>Male<input name="Gender" type="radio" value="Male"
				id="txtGender" /> Female<input name="Gender" type="radio"
				value="Female" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="add User" /></td>
		</tr>
	</table>

</form>
<script>
	radiobtn = document.getElementById("txtGender");
	radiobtn.checked = true;
</script>
<a href="../welcome"> Welcome Page</a>
</html>