package contacts;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class addOnlyNumber
 */
@WebServlet("/AddNumber")
public class AddNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNumber() {
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
		String name = request.getParameter("existingName");
		String newNumber = request.getParameter("newNumber");
		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayContacts.jsp");
		HttpSession session = request.getSession();
		String loginUser = (String) session.getAttribute("name");
		String loginName = (String) session.getAttribute("name");
		request.setAttribute("contactMap", DataBase.UserContactMap.get(loginUser));
		if (DataBase.UserContactMap.get(loginName).containsKey(name)) {
			if (Validate.validateNumber(newNumber) == 0) {
				if (!DataBase.UserContactMap.get(loginName).get(name).getContactList().contains(newNumber)) {
					DataBase.UserContactMap.get(loginName).get(name).getContactList().add(newNumber);
					request.setAttribute("message", "Number added successfully");
					dispatch.forward(request, response);
				} else {
					request.setAttribute("message",
							"Cannot add number : This number is already associated with the name");
					dispatch.forward(request, response);
				}
			} else {
				switch (Validate.validateNumber(newNumber)) {
				case 5:
					request.setAttribute("message", "Cannot add number : Phone number can only have digits");
					dispatch.forward(request, response);
					break;
				case 6:
					request.setAttribute("message", "Cannot add number : Phone number must contain 10 digits");
					dispatch.forward(request, response);
					break;
				}
			}
		} else {
			request.setAttribute("message", "Cannot add number : this name does not exist");
			dispatch.forward(request, response);
		}
	}

}
