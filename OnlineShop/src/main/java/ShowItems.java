import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ShowItems extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String cat = request.getParameter("cat");
		
		Cookie ck = new Cookie("choice", cat);
		ck.setMaxAge(60*60*24*3);
		response.addCookie(ck);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","root");
			String sql = "select pcode, ptitle from prodinfo where category = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cat);
			ResultSet rs = ps.executeQuery();
			
			out.println("<html>");
			out.println("<body>");
			out.println("<h3>Select Desired Product</h3>");
			out.println("<hr>");
			while(rs.next()) {
				String s1 = rs.getString(1);
				String s2 = rs.getString(2);
				out.println("<a href = ShowDetails?pid="+s1+">");
				out.println(s2);
				out.println("</a>");
				out.println("<br>");
			}
			out.println("<hr>");
			
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
