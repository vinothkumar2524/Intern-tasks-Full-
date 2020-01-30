package contact.datastore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String contactName = request.getParameter("searchName").toLowerCase();
		if(contactName != null && !contactName.equals(""))
		{
			request.setAttribute("searchContact", contactName);
			request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("message", "Please enter any character");
			request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
		}
		
	}

}
