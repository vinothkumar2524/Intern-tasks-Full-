package contacts;

import java.util.LinkedList;

public class ContactDetails {
	private String contactName, email, company, location;
	private LinkedList<String> numberList = new LinkedList<String>();

	ContactDetails(String contactName,String contactNumber, String email, String company, String location) {
		this.contactName = contactName;
		this.email = email;
		this.company = company;
		this.location = location;
		this.numberList.add(contactNumber);
	}

	public String getName() {
		return this.contactName;
	}
	public String getEmail() {
		return this.email;
	}
	public String getCompany() {
		return this.company;
	}
	public String getLocation() {
		return this.location;
	}
	public LinkedList<String> getContactList()
	{
		return this.numberList;
	}
	public void setName(String contactName)
	{
		this.contactName=contactName;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}
	public void setCompany(String company)
	{
		this.company=company;
	}
	public void setLocation(String location)
	{
		this.location=location;
	}
}
