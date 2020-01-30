package contact.datastore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@WebServlet("/DeleteContact")
public class DeleteContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String contactName = request.getParameter("contactName");

		String userName = session.getAttribute("userName").toString();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Query query = new Query(userName)
				.setFilter(new FilterPredicate("contactName", FilterOperator.EQUAL, contactName));
		PreparedQuery preparedQuery = datastore.prepare(query);
		Entity check = preparedQuery.asSingleEntity();
		if (check != null) {
			Key key = KeyFactory.createKey(userName, contactName);
			datastore.delete(key);
			request.setAttribute("message", "Contact deleted successfully");
			request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "Cannot delete Contact : This contact does not exist");
			request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
		}
	}

}
