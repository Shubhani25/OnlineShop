

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ShowDetails extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id = request.getParameter("pid");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","root");
			String sql = "Select * from prodinfo where pcode=?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			
			out.println("<html>");
			out.println("<body>");
			out.println("<h3>Select Desired Product</h3>");
			out.println("<hr>");
			
			rs.next();
			String s1 = rs.getString(1);//pcode
			String s2 = rs.getString(2);//ptitle
			String s3 = rs.getString(3);//pdesc
			String s4 = rs.getString(4);//category
			String s5 = rs.getString(5);//price
			
			out.println("<table border=1>");
			
			out.println("<tr>");
			out.println("<td>Pcode</td>");
			out.println("<td>"+s1+"</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Ptitle</td>");
			out.println("<td>"+s2+"</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Pdesc</td>");
			out.println("<td>"+s3+"</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Category</td>");
			out.println("<td>"+s4+"</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Price</td>");
			out.println("<td>"+s5+"</td>");
			out.println("</tr>");
			
			out.println("</table>");
					
			out.println("<hr>");
			
			out.println("<a href=CartManager?pid="+s1+">Add to Cart</a><br>");
			out.println("<a href=ShowCategories>Categories</a>");
			out.println("<br>");
			out.println("<a href=buyerhome.jsp>Customer Home</a>");
			out.println("<br>");
			
			out.println("</body>");
			out.println("</html>");
			
			
			con.close();
		}
		catch(Exception e) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
