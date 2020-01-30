package contact.datastore;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import com.google.appengine.api.datastore.*;

@WebServlet("/AddContacts")
public class AddContacts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String contactName = request.getParameter("contactName");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String company = request.getParameter("company");
		String location = request.getParameter("location");

		String userName = session.getAttribute("userName").toString();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity(userName, contactName);
		Query query = new Query(userName)
				.setFilter(new FilterPredicate("contactName", FilterOperator.EQUAL, contactName));
		PreparedQuery preparedQuery = datastore.prepare(query);
		Entity check = preparedQuery.asSingleEntity();
		List<String> list = new LinkedList<String>();
		if (check == null) {
			entity.setProperty("contactName", contactName);
			list.add(phoneNumber);
			entity.setProperty("list", list);
			entity.setProperty("email", email);
			entity.setProperty("company", company);
			entity.setProperty("location", location);
			datastore.put(entity);
			request.setAttribute("message", "contact added successfully");
			request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
			;
		} else {

			request.setAttribute("message", "Cannot updated Contact : This contact already exist!");
			request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
		}
	}

}
