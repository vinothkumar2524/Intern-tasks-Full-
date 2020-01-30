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

@WebServlet("/UpdateContact")
public class UpdateContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String contactName = request.getParameter("contactName");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String company = request.getParameter("company");
		String location = request.getParameter("location");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Entity entity = new Entity(userName, contactName);
		Query query = new Query(userName)
				.setFilter(new FilterPredicate("contactName", FilterOperator.EQUAL, contactName));
		PreparedQuery preparedQuery = datastore.prepare(query);
		Entity check = preparedQuery.asSingleEntity();
		if (check != null) {
			List<String> numberList = (List)check.getProperty("list");
			if (!numberList.contains(phoneNumber)) {
				numberList.add(phoneNumber);
				entity.setProperty("contactName", contactName);
				entity.setProperty("list", numberList);
				entity.setProperty("email", email);
				entity.setProperty("company", company);
				entity.setProperty("location", location);
				datastore.put(entity);
				request.setAttribute("message", "contact updated successfully");
				request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Cannot update Contact : This number already exist");
				request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
			}

		} else {
			request.setAttribute("message", "Cannot update Contact : This contact does not exist");
			request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
		}

	}

}
