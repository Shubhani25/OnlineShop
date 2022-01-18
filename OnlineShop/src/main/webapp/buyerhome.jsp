<% 
int n = session.getMaxInactiveInterval();

long val = session.getCreationTime();
java.util.Date dt = new java.util.Date(val);

Cookie ck[] = request.getCookies();
String userChoice = "All";
String userName = (String) session.getAttribute("username");

if(userName == null){
	response.sendRedirect("index.jsp");
}
for(Cookie c:ck){
	String name = c.getName();
	if(name.equals("choice")){
		userChoice = c.getValue();
	}
}
%>



<html>
<head>
<meta charset="ISO-8859-1">
<title>Buyer Page</title>
</head>
<body>
<h2><center>Online Shopping!</center></h2>
<h2>Buyer Page!</h2>
<h3>Welcome <%=userName%></h3>
<h4>You are on this site since <%=dt.toString() %></h4>
<h4>You are idle for <%=n %> seconds. Your session will expire</h4>
	<hr>
	<pre>
	<a href = "ShowCategories">Explore Store</a>
	<a href = "">Search Product</a>
	<a href = "DisplayCart">View Cart</a>
	<a href = "">Logout</a>
	</pre>
	<hr>
	<marquee><h3>Attractive discount on <%=userChoice%> Products</h3></marquee>
</body>
</html>