package contacts;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import javax.servlet.*;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static HashMap<String, Users> userMap = new HashMap<String, Users>();

	public RegisterServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("registerName");
		String password = request.getParameter("registerPassword");
		String rePassword = request.getParameter("registerRePassword");
		RequestDispatcher dispatch = request.getRequestDispatcher("Register.jsp");

		if (!userMap.containsKey(name)) {
			if ((name != null && password != null && rePassword != null && !name.equals("") && !password.equals("")
					&& !rePassword.equals(""))) {
				if (!name.contains(" ") && !password.contains(" ") && !rePassword.contains(" ")) {
					if (password.equals(rePassword)) {
						userMap.put(name, new Users(name, password));
						response.sendRedirect("Login.jsp");
					} else {
						request.setAttribute("errorMessage", "Passwords did not match");
						dispatch.forward(request, response);
					}
				} else {
					request.setAttribute("errorMessage", "BlankSpaces are not allowed in any fields");
					dispatch.forward(request, response);
				}
			} else {
				request.setAttribute("errorMessage", "All fields are mandatory");
				dispatch.forward(request, response);
			}
		} else {
			request.setAttribute("errorMessage", "An user already exist with this name..!");
			dispatch.forward(request, response);
		}

	}

}
