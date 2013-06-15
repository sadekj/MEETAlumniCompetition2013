package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import entities.Round;
import entities.Score;
import entities.Team;

/**
 * Servlet implementation class AllScores
 */
@WebServlet("/AllScores")
public class AllScores extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllScores() {
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
		String teamid = request.getParameter("team");
		String roundid = request.getParameter("round");
		ArrayList<Round> rounds = new ArrayList<Round>();
		ArrayList<Team> teams = new ArrayList<Team>();
		double total = 0;
		double teamTotal=0;
		int id = 0;
		try {
			if (teamid == null || teamid.equals("")) {
				teams = Database.getInstance().getAllApprovedTeams();
			} else {
				id = Integer.parseInt(teamid);
				teams.add(Database.getInstance().getTeam(id));
			}
			if (roundid == null || roundid.equals("")) {
				rounds = Database.getInstance().getAllRounds();
			} else {
				id = Integer.parseInt(roundid);
				rounds.add(Database.getInstance().getRound(id));
			}
		} catch (NumberFormatException e) {
			response.sendRedirect("AllScores");
		}
		response.getOutputStream().println("<table id='myTable' class='tablesorter'>");
		response.getOutputStream().println("<caption>Allscores</caption>");
		response.getOutputStream().println("<thead>");
		response.getOutputStream().println("<tr>");
		response.getOutputStream().println("<td>Team/Round</td>");
		for (Round currentRound : rounds)
			response.getOutputStream().println("<th scope='col'>" + currentRound.getTitle() + "</th>");
		response.getOutputStream().println("<th scope='col'>Total</th>");
		response.getOutputStream().println("</tr>");
		response.getOutputStream().println("</thead>");
		response.getOutputStream().println("<tbody>");
		for (Team currentTeam : teams) {
			response.getOutputStream().println("<tr>");
			response.getOutputStream().println("<th scope='row'>" + currentTeam.getName() + "</th>");
			for (Round currentRound : rounds) {
				ArrayList<Score> scores = Database.getInstance().getScoresPerTeamAndRound(currentTeam, currentRound);
				for (Score currentScore : scores)
					total += currentScore.getValue();
				teamTotal+=total;
				response.getOutputStream().println("<td>" + total + "</td>");
				total = 0.0;
			}
			response.getOutputStream().println("<td>" + teamTotal + "</td>");
			teamTotal=0;
			response.getOutputStream().println("</tr>");
		}
		response.getOutputStream().println("</tbody>");
		response.getOutputStream().println("</table>");
	}
}
