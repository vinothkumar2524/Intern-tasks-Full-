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
 * Servlet implementation class UpdateContact
 */
@WebServlet("/UpdateContact")
public class UpdateContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateContact() {
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
		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayContacts.jsp");
		HttpSession session = request.getSession();
		String loginUser = (String) session.getAttribute("name");
		request.setAttribute("contactMap", DataBase.UserContactMap.get(loginUser));
		
			if (DataBase.UserContactMap.get(loginUser).containsKey(contactName)) {
				DataBase.UserContactMap.get(loginUser).get(contactName).setEmail(email);
				DataBase.UserContactMap.get(loginUser).get(contactName).setCompany(company);
				DataBase.UserContactMap.get(loginUser).get(contactName).setLocation(location);
				DataBase.UserContactMap.get(loginUser).get(contactName).getContactList().add(phoneNumber);
				request.setAttribute("message", "Contact updated successfully");
				dispatch.forward(request, response);
			} else {
				request.setAttribute("message", "Cannot update contact : This contact does not exist");
				dispatch.forward(request, response);
			}
	
	}

}
