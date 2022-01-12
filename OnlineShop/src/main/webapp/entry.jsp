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

<h3><center>Product Entry Form</center></h3>
<hr>

<form action="SaveProduct">
<pre>
	ProductCode		<input type="text" name="pcode"/>
	Title			<input type="text" name="ptitle"/>
	Description		<input type="text" name="pdesc"/>
	Category		<input type="text" name="category"/>
	Price			<input type="text" name="price"/>
	
		<input type="submit" value="Add" />
</pre>
</form>
<hr>
<a href="adminhome.jsp">Admin Home</a>
</body>
</html>