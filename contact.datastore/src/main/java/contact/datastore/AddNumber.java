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

@WebServlet("/AddNumber")
public class AddNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String ExistingContactName = request.getParameter("contactName");
		String phoneNumber = request.getParameter("phoneNumber");

		String userName = session.getAttribute("userName").toString();

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(userName)
				.setFilter(new FilterPredicate("contactName", FilterOperator.EQUAL, ExistingContactName));
		PreparedQuery preparedQuery = datastore.prepare(query);
		Entity check = preparedQuery.asSingleEntity();
		if (check != null) {
			List<String> numberList = (List) check.getProperty("list");
			if (!numberList.contains(phoneNumber)) {
				if (numberList.contains("No number")) {
					numberList.remove("No number");
				}
				numberList.add(phoneNumber);
				check.setProperty("contactName", ExistingContactName);
				check.setProperty("list", numberList);
				datastore.put(check);
				request.setAttribute("message", "Number added successfully");
				request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Cannot add number : This number already exist");
				request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "Cannot add number : This name does not exist");
			request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
		}

	}

}
