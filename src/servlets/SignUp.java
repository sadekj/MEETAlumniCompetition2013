package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import entities.User;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int error=0;
		if(!Database.getInstance().isCountdownDone(1)){
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("pass");
		String repassword = request.getParameter("passC");
		if (password.length() >= 6) {
			if (password.equals(repassword)) {
				User user = new User(0, firstname, lastname, username, password, "", "Pending", email);
				if (Database.getInstance().isValid(user)) {
					boolean success = Database.getInstance().signup(user);
					if (!success)
						error = 1;//"Sign up Failed";
				}else{
					error = 3;//"Username and/or email is taken.";
				}
			} else {
				error = 4;// "Passwords don't match.";
			}
		} else {
			error = 5;// "Password Length should be at least 6 charecters long";
		}
		}else{
			error = 6;// "Sorry you can't sign up, your are late!";
		}
		if (error > 0)
			response.sendRedirect("index.jsp?e=" + error);
		else
			response.sendRedirect("index.jsp?e=0");//Thank you, your registration is awaiting approval, in order to use the service!
	}
}
