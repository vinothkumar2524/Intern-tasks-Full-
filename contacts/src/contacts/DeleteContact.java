package contacts;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
/**
 * Servlet implementation class DeleteContact
 */
@WebServlet("/DeleteContact")
public class DeleteContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contactName = request.getParameter("deleteName");
		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayContacts.jsp");
		HttpSession session = request.getSession();
		String loginUser = (String)session.getAttribute("name");
		request.setAttribute("contactMap", DataBase.UserContactMap.get(loginUser));
		if(DataBase.UserContactMap.get(loginUser).containsKey(contactName))
		{
			DataBase.UserContactMap.get(loginUser).remove(contactName);
			request.setAttribute("message", "Contact deleted successfully");
			dispatch.forward(request, response);
		}
		else
		{
			request.setAttribute("message", "Cannot Delete Contact:No Such name Exist");
			dispatch.forward(request, response);
		}
	}

}
