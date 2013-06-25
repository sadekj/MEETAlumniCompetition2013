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
			response.getOutputStream().println("<div id='allteams'>");
			response.getOutputStream().println("<h3>All " + status + " Teams</h3>");
			ArrayList<Team> teams;
			String function;
			if (status.equals("Pending") && allowed) {
				teams = Database.getInstance().getAllPendingTeams();
				function = "approveTeam";
			} else if (status.equals("Disabled") && allowed) {
				teams = Database.getInstance().getAllDisabledTeams();
				function = "enableTeam";
			} else {
				teams = Database.getInstance().getAllApprovedTeams();
				function = "disableTeam";
			}
			boolean isInTeam = Database.getInstance().isInTeam(creator);
			for (Team team : teams) {
				response.getOutputStream().println("<div class='well'>");
				response.getOutputStream().println("<a href='#' onclick='loadTeamMembers(" + team.getId() + ")'><h4>" + team.getName() + "</h4></a>");
				if(!isInTeam)
					response.getOutputStream().println("<a href='JoinTeam?id="+team.getId()+"'>Join</a>");
				if (allowed)
					response.getOutputStream().println("<button onclick='" + function + "(" + team.getId() + ")'>" + function + "</button>");
				response.getOutputStream().println("</div>");
			}
			response.getOutputStream().println("</div>");
		} else {
			response.getOutputStream().print("Not logged in");
		}
	}

}
