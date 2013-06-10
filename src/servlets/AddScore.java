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
import entities.Round;
import entities.Score;
import entities.Team;
import entities.User;

/**
 * Servlet implementation class AddScore
 */
@WebServlet("/AddScore")
public class AddScore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddScore() {
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
			Group admin = Database.getInstance().getGroup(1);
			if (Database.getInstance().isInGroup(creator, admin)) {
				String strteamid = request.getParameter("team");
				String strroundid = request.getParameter("round");
				String strvalue = request.getParameter("value");
				String description = request.getParameter("description");
				try {
					double value = Double.parseDouble(strvalue);
					int teamid = Integer.parseInt(strteamid);
					int roundid = Integer.parseInt(strroundid);

					Score score = new Score(0, value, description, creator);
					Team team = Database.getInstance().getTeam(teamid);
					Round round = Database.getInstance().getRound(roundid);
					if (Database.getInstance().addScore(score, creator, team, round))
						response.getOutputStream().print("Success");
					else
						response.getOutputStream().print("Failed");
				} catch (NumberFormatException e) {
					response.getOutputStream().print("Invalid score, team or round");
				}
			}else{
				response.getOutputStream().print("you are not an admin");
			}
		}else{
			response.getOutputStream().print("Not logged in");
		}
	}
}
