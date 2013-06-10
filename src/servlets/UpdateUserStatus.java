package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import entities.Group;
import entities.User;

/**
 * Servlet implementation class UpdateUserStatus
 */
@WebServlet("/UpdateUserStatus")
public class UpdateUserStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			User creator = (User) session.getAttribute("user");
			Group staff = Database.getInstance().getGroup(2);
			if (Database.getInstance().isInGroup(creator, staff)) {
				String struserid = request.getParameter("user");
				String status = request.getParameter("status");
				try {
					int userid = Integer.parseInt(struserid);
					User user = Database.getInstance().getUser(userid);
					boolean success = false;
					if (status.equals("Approve")) {
						success = Database.getInstance().approveUser(user);
					} else if (status.equals("Disable")) {
						success = Database.getInstance().DisableUser(user);
					}
					if (success)
						response.getOutputStream().print("Sucess");
					else
						response.getOutputStream().print("Failed");
				} catch (NumberFormatException e) {
					response.getOutputStream().print("Invalid User");
				}
			} else {
				response.getOutputStream().print("You are not staff");
			}
		} else {
			response.getOutputStream().print("Not logged in");
		}

	}
}
