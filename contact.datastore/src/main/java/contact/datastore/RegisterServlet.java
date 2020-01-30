package contact.datastore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String name = request.getParameter("registerName");
		String password = request.getParameter("registerPassword");
		String rePassword = request.getParameter("registerRePassword");

		response.setContentType("text/html");
		if ((name != null && password != null && rePassword != null && !name.equals("") && !password.equals("")
				&& !rePassword.equals(""))) {
			if (!name.contains(" ") && !password.contains(" ") && !rePassword.contains(" ")) {
				DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				Entity entity = new Entity("RegisteredUser", name);
				Query query = new Query("RegisteredUser")
						.setFilter(new FilterPredicate("name", FilterOperator.EQUAL, name));
				PreparedQuery preparedQuery = datastore.prepare(query);
				Entity check = preparedQuery.asSingleEntity();

				if (check == null) {
					if (password.equals(rePassword)) {

						entity.setProperty("name", name);
						entity.setProperty("password", password);
						datastore.put(entity);
						response.sendRedirect("Login.jsp");
					} else {
						request.setAttribute("message", "passwords mismatch");
						request.getRequestDispatcher("Register.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("message", "User already exist");
					request.getRequestDispatcher("Register.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("message", "No empty spaces allowed");
				request.getRequestDispatcher("Register.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "All fields are mandatory");
			request.getRequestDispatcher("Register.jsp").forward(request, response);
		}

	}

}
