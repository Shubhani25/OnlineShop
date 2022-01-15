<html>
<body>
<h2>Online Shopping!</h2>
<hr>
<form action="VerifyUser">
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