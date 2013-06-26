package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import entities.Countdown;
import entities.Group;
import entities.Round;
import entities.User;

/**
 * Servlet implementation class CreateRound
 */
@WebServlet("/CreateRound")
public class CreateRound extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateRound() {
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
				Group staff = Database.getInstance().getGroup(2);
				User user = (User) session.getAttribute("user");
				if (Database.getInstance().isInGroup(user, staff)) {
					String title = request.getParameter("title");
					String description = request.getParameter("description");
					String strenddate = request.getParameter("enddate");
					String endtime = request.getParameter("endtime");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                java.util.Date endDate = sdf.parse(strenddate);
	                java.sql.Date endSqlDate = new Date(endDate.getTime());
	                java.sql.Time endSqlTime = java.sql.Time.valueOf(endtime);
					Round round = new Round(0, title, description, "Closed");
					Countdown countdown = new Countdown(0, round.getTitle(), "Countdown of "+round.getTitle()+", which is is about: "+round.getDescription(), endSqlDate, endSqlTime);
					if (Database.getInstance().createRound(round,countdown))
						response.sendRedirect("rounds.jsp?header=t");
					else
						response.getOutputStream().print("Error while creating the round!");
				} else {
					response.getOutputStream().print("you are not staff");
				}
			} catch (Exception e) {
				response.getOutputStream().print("Error while creating the round!");
			}
		} else {
			response.getOutputStream().print("Not logged in");
		}
	}

}
