<%-- @page import="java.sql.Connection, java.sql.DriverManager,....."--%>
<%@page import="java.sql.*" language="java" isThreadSafe="true" contentType="text/html" %> 

<%@include file="info.jsp" %>


<%--Comments in JSP --%>
<%!
	public int data=100;
	int getTax(int price){
		int tax=0;
		if(price>=10000){
			tax=price*10/100;
		}
		else{
			tax=price*5/100;
		}
		return tax;
	}
%>
<%
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "root");
Statement smt = con.createStatement();
ResultSet rs = smt.executeQuery("select * from prodinfo");

%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Product List</h1>
<table border=2>
	<tr>
		<td>Pcode</td>
		<td>Ptitle</td>
		<td>Desc</td>
		<td>Category</td>
		<td>Price</td>
		<td>Tax</td>
	</tr>
	<%
		while (rs.next()){
			String s1=rs.getString(1);
			String s2=rs.getString(2);
			String s3=rs.getString(3);
			String s4=rs.getString(4);
			String s5=rs.getString(5);
		
	%>
	<tr>
		<td><%=s1 %></td>
		<td><%=s2 %></td>
		<td><%=s3 %></td>
		<td><%=s4 %></td>
		<td><%=s5 %></td>
		<td><%=getTax(Integer.parseInt(s5)) %></td>
		
	</tr>
	
	<%
		}
		con.close();
	%>
	
	
</table>
</body>
</html>
<%@include file="footer.jsp"
%>