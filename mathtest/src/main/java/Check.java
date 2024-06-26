import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mathtest.DBsrc;
import mathtest.MyApplicationContextConfiguration;
import mathtest.Question;

/**
 * Servlet implementation class Check
 */
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
	    		MyApplicationContextConfiguration.class);
	    DBsrc store = ctx.getBean(DBsrc.class);
		String user_answer = request.getParameter("answer");
		Question q = store.getQuestion(
				Integer.parseInt(request.getParameter("questionId")));
		PrintWriter writer = response.getWriter();
		store.saveAnswer(q, user_answer);
		writer.println("<h1>Your answer is " + user_answer + "; right is " + q.getAnswer() + "</h1>");
		writer.close();
	}

}
