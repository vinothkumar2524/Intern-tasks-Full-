package contacts;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import java.util.LinkedList;;/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//static TreeMap<String,Map<String,ContactDetails>> UserContactMap=new TreeMap<String,Map<String,ContactDetails>>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("loginName");
		String password = request.getParameter("loginPassword");
		RequestDispatcher dispatch = request.getRequestDispatcher("Login.jsp");
		request.setAttribute("contactMap", DataBase.UserContactMap.get(name));
		if(name != null && password != null && !name.equals("") && !password.equals(""))
		{
			if(RegisterServlet.userMap.containsKey(name))
			{
				if(name.equals(RegisterServlet.userMap.get(name).getName()) && password.equals(RegisterServlet.userMap.get(name).getPassword()))
				{
					request.setAttribute("message", "Loggedin Successfully");
					HttpSession session = request.getSession();
					session.setAttribute("name", name);
					if(DataBase.UserContactMap.get(name) == null)
					{
						DataBase.UserContactMap.put(name, new TreeMap<String,ContactDetails>());
						dispatch.forward(request, response);
					}
					else
					{
						dispatch.forward(request, response);
					}
				}
				else
				{
					request.setAttribute("message", "Cannot Login:Invalid Username or Password");
					dispatch.forward(request, response);
				}
			}
			else
			{
				request.setAttribute("message", "Cannot Login:Invalid Username or Password");
				dispatch.forward(request, response);
			}
		}
		else
		{
			request.setAttribute("message", "All fields are mandatory");
			dispatch.forward(request, response);
		}
	}

}
