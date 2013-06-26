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
 * Servlet implementation class AddUserToGroup
 */
@WebServlet("/AddUserToGroup")
public class AddUserToGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserToGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("user") != null) {
				User creator = (User) session.getAttribute("user");
				Group admin = Database.getInstance().getGroup(1);
				if (Database.getInstance().isInGroup(creator, admin)) {
					String strgroupid = request.getParameter("groupid");
					String struserid = request.getParameter("userid");
					int groupid = Integer.parseInt(strgroupid);
					int userid = Integer.parseInt(struserid);
					User user = Database.getInstance().getUser(userid);
					Group group = Database.getInstance().getGroup(groupid);
					if (!Database.getInstance().isInGroup(user, group)) {
						if (Database.getInstance().addUserToGroup(user, group)) {
							response.getOutputStream().print("User Added");
						} else {
							response.getOutputStream().print("Adding has failed");
						}
					} else {
						response.getOutputStream().print("User is already in a different group");
					}
				} else {
					response.getOutputStream().print("You are  not allowed to do that.");
				}
			} else {
				response.getOutputStream().print("You are not logged in!");
			}
		} catch (Exception e) {
			response.getOutputStream().print("Error, please contact your Administrator!");
		}
	}

}
