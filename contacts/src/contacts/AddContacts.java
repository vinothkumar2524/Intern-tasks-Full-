package contacts;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;

/**
 * Servlet implementation class AddContacts
 */
@WebServlet("/AddContacts")
public class AddContacts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddContacts() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String contactName = request.getParameter("contactName");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String company = request.getParameter("company");
		String location = request.getParameter("location");
		RequestDispatcher dispatchDisplay = request.getRequestDispatcher("DisplayContacts.jsp");
		HttpSession session = request.getSession();
		String loginUser = (String) session.getAttribute("name");
		request.setAttribute("contactMap", DataBase.UserContactMap.get(loginUser));

		
			if (!DataBase.UserContactMap.get(loginUser).containsKey(contactName)) {
				DataBase.UserContactMap.get(loginUser).put(contactName,
						new ContactDetails(contactName, phoneNumber, email, company, location));
				request.setAttribute("message", "contact added successfully");
				dispatchDisplay.forward(request, response);
			} else {

				request.setAttribute("message", "This name already exist");
				dispatchDisplay.forward(request, response);
			}
		
		

		
	}

}
