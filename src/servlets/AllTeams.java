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
import entities.Team;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teamid = request.getParameter("team");
		String roundid = request.getParameter("round");
		ArrayList<Round> rounds = new ArrayList<Round>();
		ArrayList<Team> teams = new ArrayList<Team>();
		double total = 0;
		int id = 0;
		try {
			if (teamid == null || teamid.equals("")) {
				teams = Database.getInstance().getAllTeams();
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
			response.sendRedirect("AllTeams");
		}
		String categories = "[";
		for (int i = 0; i < teams.size(); i++) {
			categories += "'" + teams.get(i).getName() + "'";
			if (i != teams.size() - 1)
				categories += ",";
		}
		categories += "]";
	}

}
