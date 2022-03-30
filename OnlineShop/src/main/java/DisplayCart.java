

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DisplayCart extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		ArrayList list = (ArrayList) session.getAttribute("cart");
		out.println("<html><body>");
		if(list==null) {
			out.println("<h4>Your cart is empty!</h4>");
			out.println("<h4><a href = ShowCategories>Start Buying!</a></h4>");
		}
		else {
			out.println("<h3>Your cart </h3>");
			String items = list.toString();
			items = items.replace('[', '(');
			items = items.replace(']', ')');
			String sql = "Select * from prodinfo where pcode in"+items;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","root");
				Statement smt = con.createStatement();
				ResultSet rs = smt.executeQuery(sql);
				
				out.println("<table border =1>");
				out.println("<tr>");
				out.println("<td>Pcode</td>");
				out.println("<td>Title</td>");
				out.println("<td>Decription</td>");
				out.println("<td>Category</td>");
				out.println("<td>Price</td>");
				out.println("</tr>");
				
				int sum=0;
				
				while(rs.next()) {
					String s1 = rs.getString(1);
					String s2 = rs.getString(2);
					String s3 = rs.getString(3);
					String s4 = rs.getString(4);
					String s5 = rs.getString(5);
					sum = sum+Integer.parseInt(s5);
					
					out.println("<tr>");
					out.println("<td>"+s1+"</td>");
					out.println("<td>"+s2+"</td>");
					out.println("<td>"+s3+"</td>");
					out.println("<td>"+s4+"</td>");
					out.println("<td>"+s5+"</td>");
					out.println("<td align=center><a href=RemoveItem?id="+s1+">[x]</a></td>");
					out.println("</tr>");
				}
				
				out.println("<tr>");
				out.println("<td></td>");
				out.println("<td></td>");
				out.println("<td></td>");
				out.println("<td>Total: </td>");
				out.println("<td>"+ sum +"</td>");
				out.println("</tr>");
				
				out.println("</table>");
				
				out.println("<a href=PaymentPage>Confirm Order</a>");
				out.println("<br>");
				out.println("<a href=ShowCategories>Add More Products?</a>");
				out.println("<br>");
				out.println("<a href=buyerhome.jsp>Buyer Home Page</a>");
				out.println("<br>");
				
				con.close();
			}
			catch(Exception e){
				
			}
		}
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
