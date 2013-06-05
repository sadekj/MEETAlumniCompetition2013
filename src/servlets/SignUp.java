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
@WebServlet("/Signup")
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
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("pass");
		String repassword = request.getParameter("passC");
		String error = "";
		if (password.length() >= 6) {
			if (password.equals(repassword)) {
				User user = new User(0, firstname, lastname, username, password, "", "Pending", email);
				if (!Database.getInstance().signup(user))
					error += "Sign up Failed";
			} else{
				error += "Passwords don't match.";
			}
		} else {
			error += "Password Length should be at least 6 charecters long";
		}
		if (error.length() > 0)
			response.sendRedirect("index.jsp?error=" + error);
		else
			response.sendRedirect("index.jsp?message=Thank You, Waiting the staff aproval so you can sign in!");
	}
}
