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
 * Servlet implementation class DeleteNumber
 */
@WebServlet("/DeleteNumber")
public class DeleteNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNumber() {
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
		String contactName=request.getParameter("existingName");
		String existingNumber=request.getParameter("existingNumber");
		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayContacts.jsp");
		HttpSession session = request.getSession();
		String loginUser = (String) session.getAttribute("name");
		request.setAttribute("contactMap", DataBase.UserContactMap.get(loginUser));
		if(DataBase.UserContactMap.get(loginUser).containsKey(contactName))
		{
			if(Validate.validateNumber(existingNumber)==0)
			{
				if(DataBase.UserContactMap.get(loginUser).get(contactName).getContactList().contains(existingNumber))
				{
					DataBase.UserContactMap.get(loginUser).get(contactName).getContactList().remove(existingNumber);
					request.setAttribute("message", "Number Deleted Successfully");
					dispatch.forward(request, response);
				}
				else
				{
					request.setAttribute("message", "Cannot Delete Number : no such number found");
					dispatch.forward(request, response);
				}
			}
			else
			{
				switch (Validate.validateNumber(existingNumber)) {
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
			
		}
		else
		{
			request.setAttribute("message", "Cannot Delete Number : no such name found");
			dispatch.forward(request, response);
		}
	}

}
