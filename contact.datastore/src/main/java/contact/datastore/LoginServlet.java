package contact.datastore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.*;
import com.google.appengine.api.datastore.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.sendRedirect("Login.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
			String name = request.getParameter("loginName");
			String password = request.getParameter("loginPassword");
			if (name != null && password != null && !name.equals("") && !password.equals("")) {
				DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				Query query = new Query("RegisteredUser")
						.setFilter(new FilterPredicate("name", FilterOperator.EQUAL, name));
				PreparedQuery preparedQuery = datastore.prepare(query);
				Entity check = preparedQuery.asSingleEntity();
				if (check != null) {
					String registeredPassword = check.getProperty("password").toString();
					if (registeredPassword.equals(password)) {

						session.setAttribute("userName", name);
						response.sendRedirect("DisplayContacts.jsp");
					} else {
						request.setAttribute("message", "incorrect password");
						request.getRequestDispatcher("Login.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("message", "Invalid user");
					request.getRequestDispatcher("Login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("message", "All fields are mandatory");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}

	
	}

}
