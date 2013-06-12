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
import entities.Team;
import entities.User;

/**
 * Servlet implementation class UpdateTeamStatus
 */
@WebServlet("/UpdateTeamStatus")
public class UpdateTeamStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTeamStatus() {
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
			if (Database.getInstance().isInGroup(creator, staff)) {
				String strteamid = request.getParameter("team");
				String status = request.getParameter("status");
				try {
					int teamid = Integer.parseInt(strteamid);
					Team team = Database.getInstance().getTeam(teamid);
					boolean success = false;
					if (status.equals("Approve")) {
						success = Database.getInstance().approveTeam(team);
					} else if (status.equals("Disable")) {
						success = Database.getInstance().disableTeam(team);
					}
					if (success)
						response.getOutputStream().print("Sucess");
					else
						response.getOutputStream().print("Failed");
				} catch (NumberFormatException e) {
					response.getOutputStream().print("Invalid Team");
				}
			} else {
				response.getOutputStream().print("You are not staff");
			}
		} else {
			response.getOutputStream().print("Not logged in");
		}

	}

}
