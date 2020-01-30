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
 * Servlet implementation class UpdateNumber
 */
@WebServlet("/UpdateNumber")
public class UpdateNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNumber() {
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
		String newNumber=request.getParameter("newNumber");
		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayContacts.jsp");
		HttpSession session = request.getSession();
		String loginUser = (String) session.getAttribute("name");
		request.setAttribute("contactMap", DataBase.UserContactMap.get(loginUser));
		if(DataBase.UserContactMap.get(loginUser).containsKey(contactName))
		{
			if(Validate.validateNumber(existingNumber,newNumber) == 0)
			{
				if(DataBase.UserContactMap.get(loginUser).get(contactName).getContactList().contains(existingNumber))
				{
					if(!DataBase.UserContactMap.get(loginUser).get(contactName).getContactList().contains(newNumber))
					{
						DataBase.UserContactMap.get(loginUser).get(contactName).getContactList().add(newNumber);
						DataBase.UserContactMap.get(loginUser).get(contactName).getContactList().remove(existingNumber);
						request.setAttribute("message", "Number updated successfully");
						dispatch.forward(request, response);
					}
					else
					{
						request.setAttribute("message", "Cannot Update Contact : This number already exits");
						dispatch.forward(request, response);
					}
				}
				else
				{
					request.setAttribute("message", "Cannot Update Contact : This number does not exits");
					dispatch.forward(request, response);
				}
			}
			else
			{
				switch (Validate.validateNumber(existingNumber,newNumber)) {
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
			request.setAttribute("message", "Cannot Update Contact : This name does not exits");
			dispatch.forward(request, response);
		}
	}

}
