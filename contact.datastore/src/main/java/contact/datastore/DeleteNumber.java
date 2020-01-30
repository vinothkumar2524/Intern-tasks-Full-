package contact.datastore;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@WebServlet("/DeleteNumber")
public class DeleteNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String existingName = request.getParameter("contactName");
		String existingNumber = request.getParameter("phoneNumber");

		String userName = session.getAttribute("userName").toString();

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Query query = new Query(userName)
				.setFilter(new FilterPredicate("contactName", FilterOperator.EQUAL, existingName));
		PreparedQuery preparedQuery = datastore.prepare(query);
		Entity check = preparedQuery.asSingleEntity();
		if (check != null) {

			try {
				List<String> numberList = (List) check.getProperty("list");
				if (numberList.contains(existingNumber)) {
					if (numberList.size() == 1) {
						numberList.add("No number");
						numberList.remove(existingNumber);
						check.setProperty("list", numberList);
						datastore.put(check);
						request.setAttribute("message", "Number deleted successfully");
						request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
					} else {
						numberList.remove(existingNumber);
						check.setProperty("list", numberList);
						datastore.put(check);
						request.setAttribute("message", "Number deleted successfully");
						request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("message", "Cannot delete number : This number does not exist");
					request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
				}
			} catch (Exception e) {
				request.setAttribute("message", "Cannot delete number : This number does not exist");
				request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "Cannot delete number : This name does not exist");
			request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
		}
	}

}
