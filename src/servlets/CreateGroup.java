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
import entities.User;

/**
 * Servlet implementation class CreateGroup
 */
@WebServlet("/CreateGroup")
public class CreateGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			try {
				Group admin = Database.getInstance().getGroup(1);
				User creator = (User) session.getAttribute("user");
				if (Database.getInstance().isInGroup(creator, admin)) {
					String groupName = request.getParameter("name");
					String groupDescription = request.getParameter("description");
					Group group = new Group(0, groupName, groupDescription);
					Database.getInstance().createGroup(group);
					response.sendRedirect("group.jsp?id=" + group.getId());
				} else {
					response.getOutputStream().print("You are not admin!");
				}
			} catch (Exception e) {
				response.getOutputStream().print("Somthing went wrong contact your Administrator!");
			}
		} else {
			response.getOutputStream().print("You are not logged in!");
		}
	}

}
