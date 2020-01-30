package contacts;

import java.util.regex.*;

public class Validate {
	public static int validate(String name, String number, String email, String company, String location) {
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

	public static int validateNumber(String number) {
		if (number.length() == 10) {
			if (number.matches("[0-9]+")) {
				return 0;
			} else {
				return 5; // Phone number can only have digits
			}
		} else {
			return 6;// Phone number must contain 10 digits
		}

	}

	public static int validateNumber(String oldnumber, String newNumber) {
		if (oldnumber.length() == 10 && newNumber.length() == 10) {
			if (oldnumber.matches("[0-9]+") && newNumber.matches("[0-9]+")) {
				return 0;
			} else {
				return 5; // Phone number can only have digits
			}
		} else {
			return 6;// Phone number must contain 10 digits
		}

	}
}
