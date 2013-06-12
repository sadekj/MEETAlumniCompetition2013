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
import entities.Team;
import entities.User;

/**
 * Servlet implementation class MemberList
 */
@WebServlet("/MemberList")
public class MemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberList() {
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
				String strteamid = request.getParameter("id");
				int teamid = Integer.parseInt(strteamid);
				response.getOutputStream().println("<table id='myTable' class='tablesorter' border=1>");
				response.getOutputStream().println("<caption>Team Members</caption>");
				response.getOutputStream().println("<thead>");
				response.getOutputStream().println("<tr>");
				response.getOutputStream().println("<th scope='col'>First Name</th>");
				response.getOutputStream().println("<th scope='col'>Last Name</th>");
				response.getOutputStream().println("<th scope='col'>Email</th>");
				response.getOutputStream().println("</tr>");
				response.getOutputStream().println("</thead>");
				response.getOutputStream().println("<tbody>");
				Team team = Database.getInstance().getTeam(teamid);
				ArrayList<User> users = Database.getInstance().getMembers(team);
				for (User user : users) {
					response.getOutputStream().println("<tr>");
					response.getOutputStream().println("<td>" + user.getfName() + "</td>");
					response.getOutputStream().println("<td>" + user.getlName() + "</td>");
					response.getOutputStream().println("<td>" + user.getEmail() + "</td>");
					response.getOutputStream().println("<td><button onclick='removeFromTeam("+teamid+"," + user.getId() + ")'>Remove</button></td>");
					response.getOutputStream().println("</tr>");
				}
				response.getOutputStream().println("</tbody>");
				response.getOutputStream().println("</table>");
			} catch (NumberFormatException e) {
				response.getOutputStream().print("Invalid Team");
			}
		} else {
			response.getOutputStream().print("Not logged in");
		}

	}
}
