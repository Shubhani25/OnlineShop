<%
session.setMaxInactiveInterval(150);//seconds

String v1="", v2="";
Cookie c[] = request.getCookies();
if(c!=null){
	for(int i=0; i<c.length; i++){
		Cookie ck = c[i];
		String name = ck.getName();
		if(name.equals("iud")){
			v1 = ck.getValue();
		}
		else if(name.equals("pwd")){
			v2 = ck.getValue();
		}
	}
}
%>


<html>
<body>
<h2>Online Shopping!</h2>
<hr>
<form action="VerifyUser">
<pre>
	UserID		<input type="text" name="userid" value="<%=v1%>"/>
	Password	<input type="password" name="password" value="<%=v2%>"/>
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