<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Shopping!</title>
</head>
<body>
<h2><center>Online Shopping!</center></h2>
<hr>
<form action="">
<pre>
	UserID		<input type="text" name="userid" />
	Password	<input type="password" name="password"/>
	UserType	<select name="usertype">
				<option>Customer</option>
				<option>Admin</option>
				</select>
	SavePassword	<input type="checkbox" name="save" value="Yes"/>
	<input type="submit" value="Login" />
	
</pre>
</form>
<hr>
<a href="registration.jsp">New User? Click here!</a>
</body>
</html>