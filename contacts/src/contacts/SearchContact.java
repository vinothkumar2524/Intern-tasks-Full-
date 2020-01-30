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
 * Servlet implementation class SearchContact
 */
@WebServlet("/SearchContact")
public class SearchContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchContact() {
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
		String searchString= request.getParameter("searchName");
		request.setAttribute("searchString", searchString);
		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayContacts.jsp");
		HttpSession session = request.getSession();
		String loginUser = (String) session.getAttribute("name");
		request.setAttribute("contactMap", DataBase.UserContactMap.get(loginUser));
		request.setAttribute("searchMap", DataBase.UserContactMap.get(loginUser));
		dispatch.forward(request, response);
	}

}
