package mathtest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Create cookies      
		Cookie user_id = new Cookie("user", request.getParameter("user_id"));

		// Set expiry date after 24 Hrs for both the cookies.
		//user_id.setMaxAge(60*60*24); 

		// Add both the cookies in the response header.
		response.addCookie( user_id );
		User u = new User(
				request.getParameter("name"),
				"",
				request.getParameter("pss")
		);
		DBsrc store = new DBsrc();
		store.saveToTbl(u);
		PrintWriter writer = response.getWriter();
		writer.close();
	}

}
