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
					Round round = new Round(roundid, title, description, "");
					if (Database.getInstance().updateRound(round))
						response.sendRedirect("round.jsp?id="+roundid);
					else
						response.getOutputStream().print("Error while creating the round!");
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
