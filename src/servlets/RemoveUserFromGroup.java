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
 * Servlet implementation class RemoveUserFromGroup
 */
@WebServlet("/RemoveUserFromGroup")
public class RemoveUserFromGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveUserFromGroup() {
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
				User creator = (User) session.getAttribute("user");
				Group admin = Database.getInstance().getGroup(1);
				String strgroupid = request.getParameter("groupid");
				String struserid = request.getParameter("userid");
				int groupid = Integer.parseInt(strgroupid);
				int userid = Integer.parseInt(struserid);
				if (Database.getInstance().isInGroup(creator, admin)) {
					User user = Database.getInstance().getUser(userid);
					Group group = Database.getInstance().getGroup(groupid);
					if (Database.getInstance().isInGroup(user,group)) {
						if (Database.getInstance().removeUserFromGroup(user, group)) {
							response.getOutputStream().print("User Removed");
						} else {
							response.getOutputStream().print("Removig has failed");
						}
					} else {
						response.getOutputStream().print("User is already not in this group");
					}
				} else {
					response.getOutputStream().print("You are not allowed to do this action");
				}
			} catch (Exception e) {
				response.getOutputStream().print("Error, please contact your Administrator!");
				e.printStackTrace();
			}
		} else {
			response.getOutputStream().print("You are not logged in!");
		}
	}

}
