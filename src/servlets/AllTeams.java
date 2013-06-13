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
import entities.Team;
import entities.User;

/**
 * Servlet implementation class AllTeams
 */
@WebServlet("/AllTeams")
public class AllTeams extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllTeams() {
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
			User creator = (User) session.getAttribute("user");
			Group staff = Database.getInstance().getGroup(2);
			String status = "Approved";
			boolean allowed = Database.getInstance().isInGroup(creator, staff);
			if (allowed && request.getParameter("status") != null && !request.getParameter("status").equals("null")) {
				status = request.getParameter("status");
			}
			response.getOutputStream().println("<table id='myTable' class='tablesorter' border=1>");
			response.getOutputStream().println("<caption>All " + status + " Teams</caption>");
			response.getOutputStream().println("<thead>");
			response.getOutputStream().println("<tr>");
			response.getOutputStream().println("<th scope='col'>Name</th>");
			response.getOutputStream().println("</tr>");
			response.getOutputStream().println("</thead>");
			response.getOutputStream().println("<tbody>");
			ArrayList<Team> teams;
			String function;
			if (status.equals("Pending")) {
				teams = Database.getInstance().getAllPendingTeams();
				function = "approveTeam";
			} else if (status.equals("Disabled")) {
				teams = Database.getInstance().getAllDisabledTeams();
				function = "enableTeam";
			} else {
				teams = Database.getInstance().getAllApprovedTeams();
				function = "disableTeam";
			}
			for (Team team : teams) {
				response.getOutputStream().println("<tr>");
				response.getOutputStream().println("<td>" + team.getName() + "</td>");
				if (allowed)
					response.getOutputStream().println("<td><button onclick='" + function + "(" + team.getId() + ")'>" + function + "</button></td>");
				response.getOutputStream().println("</tr>");
			}
			response.getOutputStream().println("</tbody>");
			response.getOutputStream().println("</table>");
		} else {
			response.getOutputStream().print("Not logged in");
		}
	}

}
