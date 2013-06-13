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
 * Servlet implementation class AllPendingUsers
 */
@WebServlet("/AllUsers")
public class AllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllUsers() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
			String status = "Approved";
			boolean allowed = Database.getInstance().isInGroup(creator, staff);
			if (allowed && request.getParameter("status") != null && !request.getParameter("status").equals("null")) {
				status = request.getParameter("status");
			}
			response.getOutputStream().println("<table id='myTable' class='tablesorter' border=1>");
			response.getOutputStream().println("<caption>All " + status + " Users</caption>");
			response.getOutputStream().println("<thead>");
			response.getOutputStream().println("<tr>");
			response.getOutputStream().println("<th scope='col'>First Name</th>");
			response.getOutputStream().println("<th scope='col'>Last Name</th>");
			response.getOutputStream().println("<th scope='col'>Email</th>");
			response.getOutputStream().println("</tr>");
			response.getOutputStream().println("</thead>");
			response.getOutputStream().println("<tbody>");
			ArrayList<User> users;
			String function;
			if (status.equals("Pending") && allowed) {
				users = Database.getInstance().getAllPendingUsers();
				function = "approve";
			} else if (status.equals("Disabled") && allowed) {
				users = Database.getInstance().getAllDisabledUsers();
				function = "enable";
			} else {
				users = Database.getInstance().getAllApprovedUsers();
				function = "disable";
			}
			for (User user : users) {
				response.getOutputStream().println("<tr>");
				response.getOutputStream().println("<td>" + user.getfName() + "</td>");
				response.getOutputStream().println("<td>" + user.getlName() + "</td>");
				response.getOutputStream().println("<td>" + user.getEmail() + "</td>");
				response.getOutputStream().println("<td><button onclick='" + function + "(" + user.getId() + ")'>" + function + "</button></td>");
				response.getOutputStream().println("</tr>");
			}
			response.getOutputStream().println("</tbody>");
			response.getOutputStream().println("</table>");
		} else {
			response.getOutputStream().print("Not logged in");
		}
	}

}
