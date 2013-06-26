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
 * Servlet implementation class UpdateRound
 */
@WebServlet("/UpdateRound")
public class UpdateRound extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRound() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			try {
				Group staff = Database.getInstance().getGroup(2);
				User user = (User) session.getAttribute("user");
				if (Database.getInstance().isInGroup(user, staff)) {
					String strroundid = request.getParameter("id");
					int roundid = Integer.parseInt(strroundid);
					String title = request.getParameter("title");
					String description = request.getParameter("description");
					String strenddate = request.getParameter("enddate");
					String endtime = request.getParameter("endtime");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                java.util.Date endDate = sdf.parse(strenddate);
	                java.sql.Date endSqlDate = new Date(endDate.getTime());
	                java.sql.Time endSqlTime = java.sql.Time.valueOf(endtime);
					Round round = new Round(0, title, description, "Closed");
					Round dbRound = Database.getInstance().getRound(roundid);
					Countdown dbCountdown = Database.getInstance().getCountdown(dbRound);
					Countdown countdown = new Countdown(dbCountdown.getId(), round.getTitle(), "Countdown of "+round.getTitle()+", which is is about: "+round.getDescription(), endSqlDate, endSqlTime);
					if (Database.getInstance().updateRound(round,countdown))
						response.sendRedirect("round.jsp?id="+roundid);
					else
						response.getOutputStream().print("Error while saving the round!");
				} else {
					response.getOutputStream().print("you are not staff");
				}
			} catch (Exception e) {
				response.getOutputStream().print("Error while saving the round!");
			}
		} else {
			response.getOutputStream().print("Not logged in");
		}
	}
}
