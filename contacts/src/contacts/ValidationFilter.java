package contacts;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class ValidationFilter
 */
@WebFilter("/ValidationFilter")
public class ValidationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ValidationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String contactName = request.getParameter("contactName");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String company = request.getParameter("company");
		String location = request.getParameter("location");
		if(this.validate(contactName, phoneNumber, email, company, location) == 0)
		{
			chain.doFilter(request, response);
		}
		else
		{
			switch (Validate.validate(contactName, phoneNumber, email, company, location)) {
			case 1:
				request.setAttribute("message", "Cannot add contact : Phone number can only have digits");
				request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
				break;
			case 2:
				request.setAttribute("message", "Cannot add contact : Phone number must contain 10 digits");
				request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
				break;
			case 3:
				request.setAttribute("message", "Cannot add contact : only alphanumeric characters are allowed");
				request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
				break;
			case 4:
				request.setAttribute("message", "Cannot add contact : All fields are mandatory");
				request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
				break;
			}
		}
		
	}
	public  int validate(String name, String number, String email, String company, String location) {
		if (name != null && company != null && location != null && !name.equals("") && !company.equals("")
				&& !location.equals("")) {
			if (name.matches("[a-zA-Z0-9]+") ) {
				if (number.length() == 10) {
					if (number.matches("[0-9]+")) {
						return 0;
					} else {
						return 1; // Phone number can only have digits
					}
				} else {
					return 2;// Phone number must contain 10 digits
				}
			} else {
				return 3; // only alphanumeric characters are allowed
			}
		} else {
			return 4; // Name and phone number fields are mandatory
		}
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
