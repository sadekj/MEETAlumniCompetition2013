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
 * Servlet implementation class JoinTeam
 */
@WebServlet("/JoinTeam")
public class JoinTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinTeam() {
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
		if(session.getAttribute("user")!=null){
			User user  = (User)session.getAttribute("user");
			if(!Database.getInstance().isInTeam(user)){
				try{
					String strteamid = request.getParameter("id");
					int teamid = Integer.parseInt(strteamid);
					Team team = Database.getInstance().getTeam(teamid);
					if(Database.getInstance().addUserToTeam(user, team)){
						response.sendRedirect("team.jsp?id="+teamid);
					}else{
						response.getOutputStream().print("Problem while joining!");
					}
				}catch(Exception e){
					response.getOutputStream().print("Invalid team");
				}
			}else{
				response.getOutputStream().print("You can't be in two Teams at once!");
			}
		}else{
			response.getOutputStream().print("Not logged in!");
		}
	}

}
