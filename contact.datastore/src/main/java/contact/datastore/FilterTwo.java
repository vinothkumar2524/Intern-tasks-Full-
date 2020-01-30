package contact.datastore;
//Filter file for validating AddNumber and DeleteNumber forms
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/FilterTwo")
public class FilterTwo implements Filter {

    
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String contactName = request.getParameter("contactName");
		String phoneNumber = request.getParameter("phoneNumber");
		
		
			switch (validate(contactName, phoneNumber)) {
			case 0:chain.doFilter(request, response);
			break;
			case 1:
				request.setAttribute("message", "Cannot add contact : Phone number must contain 10 digits");
				request.getRequestDispatcher("DisplayContacts.jsp").forward(request, response);
				break;
			case 2:
				request.setAttribute("message", "Cannot add contact : Phone number can only have digits ");
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
	public int validate(String contactName, String phoneNumber) {
		if (contactName != null && phoneNumber != null && !contactName.equals("") && !phoneNumber.equals("")) {
			if (contactName.matches("[a-zA-Z0-9]+")) {
				if (phoneNumber.matches("[0-9]+")) {
					if (phoneNumber.length() == 10) {
						return 0;
					} else {
						return 1;
					}
				} else {
					return 2;
				}
			} else
			{
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
