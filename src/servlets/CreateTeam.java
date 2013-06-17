package servlets;

import java.io.IOException;

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
 * Servlet implementation class CreatTeam
 */
@WebServlet("/CreateTeam")
public class CreateTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateTeam() {
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
			try {
				User creator = (User) session.getAttribute("user");
				String teamName = request.getParameter("name");
				String teamDescription = request.getParameter("description");
				Team team = new Team(0, teamName, teamDescription, "Approved");
				team = Database.getInstance().createTeam(team);
				Database.getInstance().addUserToTeam(creator, team);
				response.sendRedirect("team.jsp?id=" + team.getId());
			} catch (Exception e) {
				response.getOutputStream().print("Somthing went wrong contact your Administrator!");
			}
		} else {
			response.getOutputStream().print("You are not logged in!");
		}
	}
}
