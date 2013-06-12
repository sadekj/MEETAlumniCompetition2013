package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import entities.Team;
import entities.User;

/**
 * Servlet implementation class RemoveUserFromTeam
 */
@WebServlet("/RemoveUserFromTeam")
public class RemoveUserFromTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveUserFromTeam() {
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
		try {
			String strteamid = request.getParameter("teamid");
			String struserid = request.getParameter("userid");
			int teamid = Integer.parseInt(strteamid);
			int userid = Integer.parseInt(struserid);
			User user = Database.getInstance().getUser(userid);
			Team team = Database.getInstance().getTeam(teamid);
			if (Database.getInstance().isInTeam(user)) {
				if (Database.getInstance().removeUserFromTeam(user, team)) {
					response.getOutputStream().print("User Removed");
				} else {
					response.getOutputStream().print("Removig has failed");
				}
			} else {
				response.getOutputStream().print("User is already not in this team");
			}
		} catch (Exception e) {
			response.getOutputStream().print("Error, please contact your Administrator!");
		}
	}

}
