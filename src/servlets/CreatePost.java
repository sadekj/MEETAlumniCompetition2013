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
import entities.Post;
import entities.Round;
import entities.User;

/**
 * Servlet implementation class CreatePost
 */
@WebServlet("/CreatePost")
public class CreatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreatePost() {
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
					String strroundid = request.getParameter("roundid");
					int roundid = Integer.parseInt(strroundid);
					Round round = Database.getInstance().getRound(roundid);
					Post post = new Post(0, title, description, user, round);
					if (Database.getInstance().addPost(post))
						response.sendRedirect("round.jsp?id=" + roundid);
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
