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

<h3><center>Registration</center></h3>
<hr>

<form action="SaveUser">
<pre>
	UserID		<input type="text" name="userid"/>
	Password	<input type="password" name="password"/>
	UserName	<input type="text" name="username"/>
	Address		<input type="text" name="address"/>
	Mobile		<input type="text" name="mobile"/>
	Email		<input type="text" name="email"/>
	
		<input type="submit" value="Registration" />
</pre>
</form>
<hr>
<a href="index.jsp">Home</a>
</body>
</html>