package contact.datastore;
//filter file for validating UpdateNumber form
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/FilterThree")
public class FilterThree implements Filter {

	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String existingName = request.getParameter("existingName");
		String existingNumber = request.getParameter("existingNumber");
		String newNumber = request.getParameter("newNumber");
		switch(validate(existingName,existingNumber,newNumber))
		{
		case 0:chain.doFilter(request, response);
		break;
		case 1:
			request.setAttribute("message", "Cannot update contact : Phone number must contain 10 digits");
			request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
			break;
		case 2:
			request.setAttribute("message", "Cannot update contact : Phone number can only have digits");
			request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
			break;
		case 3:
			request.setAttribute("message", "Cannot update contact : only alphanumeric characters are allowed");
			request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
			break;
		case 4:
			request.setAttribute("message", "Cannot update contact : All fields are mandatory");
			request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
			break;
		}
		
		
	}

	public int validate(String existingName, String existingNumber, String newNumber) {
		if (existingName != null && !existingName.equals("")) {
			if (existingName.matches("[a-zA-Z0-9]+")) {
				if (existingNumber.matches("[0-9]+") && newNumber.matches("[0-9]+")) {
					if (existingNumber.length() == 10 && newNumber.length() == 10) {
						return 0;
					} else {
						return 1;
					}
				} else {
					return 2;
				}
			} else {
				return 3;
			}
		} else {
			return 4;
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
