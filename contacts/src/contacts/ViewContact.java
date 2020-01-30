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
 * Servlet implementation class ViewContact
 */
@WebServlet("/ViewContact")
public class ViewContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewContact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contactName=request.getParameter("contactName");
		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayContacts.jsp");
		HttpSession session = request.getSession();
		String loginUser = (String) session.getAttribute("name");
		request.setAttribute("contactMap", DataBase.UserContactMap.get(loginUser));
		if(DataBase.UserContactMap.get(loginUser).containsKey(contactName))
		{
			request.setAttribute("contactName", DataBase.UserContactMap.get(loginUser).get(contactName).getName());
			request.setAttribute("contactNumbers", DataBase.UserContactMap.get(loginUser).get(contactName).getContactList());
			request.setAttribute("contactEmail", DataBase.UserContactMap.get(loginUser).get(contactName).getEmail());
			request.setAttribute("contactCompany", DataBase.UserContactMap.get(loginUser).get(contactName).getCompany());
			request.setAttribute("contactLocation", DataBase.UserContactMap.get(loginUser).get(contactName).getLocation());
			dispatch.forward(request, response);
		}
		else
		{
			request.setAttribute("message", "Cannot display contact information : No such contact exist");
			dispatch.forward(request, response);
		}
	}

}
