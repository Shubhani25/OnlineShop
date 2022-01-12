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

public class SaveProduct extends HttpServlet {
	Connection con;
	PreparedStatement ps;
	
	@Override
	public void init() throws ServletException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","root");
			String sql = "Insert into prodinfo values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		try{
			con.close();
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		try {
			
			String s1 = request.getParameter("pcode");
			String s2 = request.getParameter("ptitle");
			String s3 = request.getParameter("pdesc");
			String s4 = request.getParameter("category");
			String s5 = request.getParameter("price");
			
						
			ps.setString(1, s1);
			ps.setString(2, s2);
			ps.setString(3, s3);
			ps.setString(4, s4);
			ps.setString(5, s5);
						
			ps.executeUpdate();
			
			out.println("<html><body>"); 
			out.println("<h3>Product Added Successful</h3>");
			out.println("<h4><a href = entry.jsp>Click to add more</a></h4>");
			out.println("</body></html>");
			
			
		}
		catch(Exception e) {
			out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
