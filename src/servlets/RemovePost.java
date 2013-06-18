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
import entities.User;

/**
 * Servlet implementation class RemovePost
 */
@WebServlet("/RemovePost")
public class RemovePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemovePost() {
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
		if(session.getAttribute("user")!=null){
			try{
				User user = (User)session.getAttribute("user");
				Group admin = Database.getInstance().getGroup(1);
				if(Database.getInstance().isInGroup(user, admin)){
					String strpostid = request.getParameter("id");
					int postid = Integer.parseInt(strpostid);
					Post post = Database.getInstance().getPost(postid);
					if(Database.getInstance().removePost(post)){
						response.getOutputStream().print("Post Removed");
					}else{
						response.getOutputStream().print("Removing Failed!");
					}
				}else{
					response.getOutputStream().print("You are not an admin!");
				}
			}catch(Exception e){
				response.getOutputStream().print("Internal Error!");
			}
		}else{
			response.getOutputStream().print("Not Logged In!");
		}
	}

}
