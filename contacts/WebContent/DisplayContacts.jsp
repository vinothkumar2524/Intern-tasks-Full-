<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DisplayContacts</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		if (session.getAttribute("name") == null) {
			response.sendRedirect("Login.jsp");
		}
	%>
	<%
		out.println("<table align='right'>");
		out.println("<p align='center'><h3><u>Welcome :" + session.getAttribute("name") + "</u></h3></p>");
		out.println("</table>");
	%>
	<form method="post" action="Logout">
		<button type="submit">Logout</button>
	</form>
	<table border="1">
		<tr>
			<td>
				<h2>Search contact</h2>
				<form method="post" action="SearchContact">
					<table>
						<tr>
							<td>Name :</td>
							<td><input type="text" name="searchName"
								placeholder="Enter name"></td>

						</tr>
						<tr>
							<td><Button type="submit">Search</Button></td>
						</tr>
						<tr>

						</tr>
					</table>
				</form>

			</td>

			<td>
				<h2>Add new contact</h2>
				<form method="post" action="AddContacts">
					<table>
						<tr>
							<td>Name :</td>
							<td><input type="text" name="contactName"
								placeholder="Enter name"></td>
						</tr>
						<tr>
							<td>Phone Number :</td>
							<td><input type="text" name="phoneNumber"
								placeholder="Enter phone number"></td>
						</tr>

						<tr>
							<td>E-mail :</td>
							<td><input type="email" name="email"
								placeholder="Enter e-mail"></td>
						</tr>

						<tr>
							<td>Company :</td>
							<td><input type="text" name="company"
								placeholder="Enter company name"></td>
						</tr>

						<tr>
							<td>Location :</td>
							<td><input type="text" name="location"
								placeholder="Enter Location"></td>
						</tr>
						<tr>
							<td><Button type="submit">Add</Button></td>
						</tr>

					</table>
				</form>
			</td>
			<td>
				<h2>Update contact</h2>
				<form method="post" action="UpdateContact">
					<table>
						<tr>
							<td>Name :</td>
							<td><input type="text" name="contactName"
								placeholder="Enter name"></td>
						</tr>
						<tr>
							<td>Phone Number :</td>
							<td><input type="text" name="phoneNumber"
								placeholder="Enter phone number"></td>
						</tr>

						<tr>
							<td>E-mail :</td>
							<td><input type="email" name="email"
								placeholder="Enter e-mail"></td>
						</tr>

						<tr>
							<td>Company :</td>
							<td><input type="text" name="company"
								placeholder="Enter company name"></td>
						</tr>

						<tr>
							<td>Location :</td>
							<td><input type="text" name="location"
								placeholder="Enter Location"></td>
						</tr>
						<tr>
							<td><Button type="submit">Update</Button></td>
						</tr>
					</table>
				</form>
			</td>

			<td>
				<h2>Delete contact</h2>
				<form method="post" action="DeleteContact">
					<table>
						<tr>
							<td>Enter existing Name :</td>
							<td><input type="text" name="deleteName"
								placeholder="Enter name"></td>
						</tr>
						<tr>
							<td><Button type="submit">Delete</Button></td>
						</tr>
					</table>
				</form>
			</td>
			<td><h4>View Contacts</h4>
				<form method="post" action="ViewContact">
					<table>
						<tr>
							<td>Name :</td>
							<td><input type="text" name="contactName"placeholder="enter name"></td>
						</tr>
						<tr>
							<td><button type="submit">view</button></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>

		<tr>
			<td>
				<%
					Map<String, ContactDetails> contactMap = (Map<String, ContactDetails>) request.getAttribute("contactMap");
					String searchString = (String) request.getAttribute("searchString");
					int count = 0;
					if (request.getAttribute("searchMap") != null) {
						for (Map.Entry<String, ContactDetails> entry : contactMap.entrySet()) {
							if (entry.getValue().getName().contains(searchString)) {
								count++;
								out.println("<table>");
								out.println("<tr>");
								out.println("<td><b>" + entry.getValue().getName() + "</b></td>");
								Iterator iterator = entry.getValue().getContactList().iterator();
								while (iterator.hasNext()) {
									out.println("<td> : " + iterator.next() + "</td>");
								}
								out.println("</tr>");
								out.println("</table>");
							}
						}
						if (count == 0) {
							out.println("No matching contacts");
						} else {
							out.println(count + " matches found");
						}
					}
				%>
			</td>
			<td><h4>Add Only Number</h4>
				<form method="post" action="AddNumber">
					<table>
						<tr>
							<td>Enter existing name :</td>
							<td><input type="text" name="existingName"
								placeholder="enter existing name"></td>
						</tr>

						<tr>
							<td>Enter new number :</td>
							<td><input type="text" name="newNumber"
								placeholder="enter new number"></td>
						</tr>
						<tr>
							<td><Button type="submit">Add Number</Button></td>
						</tr>
					</table>
				</form></td>
			<td><h4>Update Only Number</h4>
				<form method="post" action="UpdateNumber">
					<table>
						<tr>
							<td>Enter existing name :</td>
							<td><input type="text" name="existingName"
								placeholder="enter existing name"></td>
						</tr>
						<tr>
							<td>Enter existing number :</td>
							<td><input type="text" name="existingNumber"
								placeholder="enter existing number"></td>
						</tr>
						<tr>
							<td>Enter new number :</td>
							<td><input type="text" name="newNumber"
								placeholder="enter new number"></td>
						</tr>
						<tr>
							<td><Button type="submit">Update Number</Button></td>
						</tr>
					</table>
				</form></td>
			<td><h4>Delete Only Number</h4>
				<form method="post" action="DeleteNumber">
					<table>
						<tr>
							<td>Enter existing name :</td>
							<td><input type="text" name="existingName"
								placeholder="enter existing name"></td>
						</tr>
						<tr>
							<td>Enter existing number :</td>
							<td><input type="text" name="existingNumber"
								placeholder="enter existing number"></td>
						</tr>
						<tr>
							<td><Button type="submit">Delete Number</Button></td>
						</tr>
					</table>
				</form></td>
				
				<%if(request.getAttribute("contactName")!=null)
				{
					
					out.println("<table>");
					out.println("<tr><td>Name :</td><td>"+request.getAttribute("contactName")+"</td></tr>");
					out.println("<tr><td>Numbers :</td>");
					List contactList=(List)request.getAttribute("contactNumbers");
					Iterator iterateList=contactList.iterator();
					while(iterateList.hasNext())
					{
						out.println("<td> : "+iterateList.next()+"</td>");
					}
					out.println("</tr>");
					out.println("<tr><td>E-mail :</td><td>"+request.getAttribute("contactEmail")+"</td></tr>");
					out.println("<tr><td>Company :</td><td>"+request.getAttribute("contactCompany")+"</td></tr>");
					out.println("<tr><td>Location :</td><td>"+request.getAttribute("contactLocation")+"</td></tr>");
					
				}
					%>
					
		</tr>
	</table>
	<%@ page import="java.util.*"%>
	<%@ page import="contacts.ContactDetails"%>
	<%
		if (request.getAttribute("message") != null) {
			out.println(request.getAttribute("message"));
		}
	%>
	<h1>
		<p align="center">
			<u>CONTACTS</u>
		</p>
	</h1>
	<table width="1000" align="center">
		<tr>
			<td>
				<%
					if (contactMap != null) {

						out.println("<table align='center'>");
						for (Map.Entry<String, ContactDetails> entry : contactMap.entrySet()) {
							out.println("<tr><td><b>");
							out.println(entry.getKey());
							out.println("</b></td>");
							Iterator<String> iterate = entry.getValue().getContactList().iterator();
							while (iterate.hasNext()) {
								out.println("<td>  : " + iterate.next() + "</td>");
							}
							out.println("</tr");
						}
						out.println("</table>");
					} else {
						out.println("<table align='center'>");
						out.println("<tr><td>");
						out.println("No contacts");
						out.println("</td></tr>");
						out.println("</table>");
					}
				
				%>
			</td>
		</tr>
	</table>
</body>
</html>