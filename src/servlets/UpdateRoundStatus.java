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
import entities.User;

/**
 * Servlet implementation class UpdateRoundStatus
 */
@WebServlet("/UpdateRoundStatus")
public class UpdateRoundStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRoundStatus() {
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
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			User creator = (User) session.getAttribute("user");
			Group staff = Database.getInstance().getGroup(2);
			if (Database.getInstance().isInGroup(creator, staff)) {
				String strroundid = request.getParameter("round");
				try {
					String status = request.getParameter("status");
					int roundid = Integer.parseInt(strroundid);
					Round round = Database.getInstance().getRound(roundid);
					boolean success = false;
					if (status.equals("Open")) {
						success = Database.getInstance().openRound(round);
					} else if (status.equals("Close")) {
						success = Database.getInstance().closeRound(round);
					}
					if (success)
						response.getOutputStream().print("Sucess");
					else
						response.getOutputStream().print("Failed");
				} catch (NumberFormatException e) {
					response.getOutputStream().print("Invalid Round");
				}
			} else {
				response.getOutputStream().print("You are not staff");
			}
		} else {
			response.getOutputStream().print("Not logged in");
		}
	}

}
