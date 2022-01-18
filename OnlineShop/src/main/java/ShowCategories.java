import jakarta.servlet.ServletConfig;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ShowCategories extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		ArrayList list = (ArrayList)session.getAttribute("cart");
		int no =0;
		if(list!=null) {
			no=list.size();
		}
		
		String name = (String) session.getAttribute("username");
		
		if(name == null){
			response.sendRedirect("index.jsp");
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","root");
			String sql = "select distinct category from prodinfo order by category";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			out.println("<html><body>");
			out.println("<h2>Welcome"+name+"</h2>");
			out.println("<h3>Total items in cart "+no+"</h3>");
			out.println("<h3>Select the desired category:</h3>");
			out.println("<hr>");
			while(rs.next()) {
				String s = rs.getString(1);
				out.println("<a href=ShowItems?cat="+s+">");
				out.println(s);
				out.println("</a>");
				out.println("<br>");
			}
			out.println("</body></html>");
			
			con.close();
		}
		catch(Exception e) {
			out.println(e);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
