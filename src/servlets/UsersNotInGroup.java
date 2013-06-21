package servlets;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class UsersNotInGroup
 */
@WebServlet("/UsersNotInGroup")
public class UsersNotInGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsersNotInGroup() {
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
		if (session.getAttribute("user") != null) {
			try {
				User creator = (User) session.getAttribute("user");
				Group staff = Database.getInstance().getGroup(2);
				String strgroupid = request.getParameter("id");
				int groupid = Integer.parseInt(strgroupid);
				response.getOutputStream().println("<table class='tablesorter' border=1>");
				response.getOutputStream().println("<caption>Available Users</caption>");
				response.getOutputStream().println("<thead>");
				response.getOutputStream().println("<tr>");
				response.getOutputStream().println("<th scope='col'>First Name</th>");
				response.getOutputStream().println("<th scope='col'>Last Name</th>");
				response.getOutputStream().println("<th scope='col'>Email</th>");
				response.getOutputStream().println("</tr>");
				response.getOutputStream().println("</thead>");
				response.getOutputStream().println("<tbody>");
				Group group = Database.getInstance().getGroup(groupid);
				ArrayList<User> users = Database.getInstance().getUsersNotInGroup(group);
				for (User user : users) {
					response.getOutputStream().println("<tr>");
					response.getOutputStream().println("<td>" + user.getfName() + "</td>");
					response.getOutputStream().println("<td>" + user.getlName() + "</td>");
					response.getOutputStream().println("<td>" + user.getEmail() + "</td>");
					if (Database.getInstance().isInGroup(creator, staff)) {
					response.getOutputStream().println("<td><button onclick='addToGroup(" + groupid + "," + user.getId() + ")'>Add</button></td>");
					}
					response.getOutputStream().println("</tr>");
				}
				response.getOutputStream().println("</tbody>");
				response.getOutputStream().println("</table>");
			} catch (NumberFormatException e) {
				response.getOutputStream().print("Invalid Group");
			}
		} else {
			response.getOutputStream().print("Not logged in");
		}

	}

}
