import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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

public class VerifyUser extends HttpServlet {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	@Override
	public void init() throws ServletException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","root");
			String sql = "Select username from userinfo where userid=? and password=?";
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
        String s1=request.getParameter("userid");
        String s2=request.getParameter("password");
        String s3=request.getParameter("usertype");
        
        if(s3.equals("Admin")){
            if(s1.equals("Admin") && s2.equals("admin")){
                //out.println("WELCOME ADMIN");
                response.sendRedirect("adminhome.jsp");
            }
            else{
                out.println("INVALID ADMIN CREDENTIALS");
            }
        }
        else{
            try{
                ps.setString(1, s1);
                ps.setString(2, s2);
                rs=ps.executeQuery();
               
                boolean found=rs.next();
                
                if(found==true){
                    String name=rs.getString(1);
                    HttpSession session=request.getSession();
                    session.setAttribute("username", name);
                   
                    String sp=request.getParameter("save");
                    if(sp!=null){
                        Cookie c1=new Cookie("uid",s1);
                        Cookie c2=new Cookie("pwd", s2);
                        
                        c1.setMaxAge(60*60*24*7);
                        c2.setMaxAge(60*60*24*7);
                        
                        response.addCookie(c1);
                        response.addCookie(c2);
                        
                    }
                    //out.println("WELCOME BUYER");
                    RequestDispatcher rd=request.getRequestDispatcher("buyerhome.jsp");
                    rd.forward(request, response);
                }
                else{
                    out.println("INVALID BUYER CREDENTIALS");
                }
            }
            catch(Exception e){
            	out.println(e);
            }
        }
    }
					
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
