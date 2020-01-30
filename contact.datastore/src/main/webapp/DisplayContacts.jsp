<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DisplayContacts</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body>
	<div style="background-color: grey; padding-top: 30px;">
		<div align="center"
			style="font-size: 40px; text-shadow: 0px 0px 10px yellow; background-color: #d4e1ff; margin-left: 35%; margin-right: 35%; border-radius: 20px; padding: 10px;">
			<%
				response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
				if (session.getAttribute("userName") == null) {
					response.sendRedirect("Login.jsp");
				}
				out.println("<table align='right'>");
				out.println("<p align='center'><h3>Welcome :" + session.getAttribute("userName") + "</h3></p>");
				out.println("</table>");
			%>

		</div>
		<form method="get" action="LogoutServlet" align="center"
			style="margin-top: 5px; margin-bottom: 5px;">
			<button type="submit" class="btn btn-danger">Logout</button>
		</form>

		<div class="row">
			<div class="col-lg-3">
				<div class="card"
					style="width: 19rem; box-shadow: 10px 20px 30px black;">

					<div class="card-body" style="background-color: #d4e1ff;">
						<h5 class="card-title">Search contact</h5>
						<p class="card-text">
						<form method="post" action="SearchServlet">
							<table>
								<tr>
									<td>Name :</td>
									<td><input type="text" name="searchName"
										placeholder="Enter name"></td>

								</tr>
								<tr>
									<td><Button type="submit" class="btn btn-primary">Search</Button></td>
								</tr>
								<tr>

								</tr>
							</table>
						</form>
						<div
							style="height: 8rem; overflow: scroll; box-shadow: 0px 0px 10px black; background-color: white; padding-left: 10px; margin-top: 10px;">
							<%
								if (request.getAttribute("searchContact") != null) {
									int count = 0;
									DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
									Query query = new Query(session.getAttribute("userName").toString());
									PreparedQuery preparedQuery = datastore.prepare(query);
									for (Entity entity : preparedQuery.asIterable()) {

										if (entity.getProperty("contactName").toString().toLowerCase()
												.contains(request.getAttribute("searchContact").toString())) {
											count++;
											out.println(entity.getProperty("contactName"));
											out.println("<br>");
										}
									}
									if (count == 1) {
										out.println(count + " matching contact");
									} else if (count > 1) {
										out.println(count + " matching contacts");
									} else {
										out.println("No matching contacts");
									}
								}
							%>
						</div>
						<br>
						<h4>View Contacts</h4>
						<form method="post" action="ViewContact">
							<table>
								<tr>
									<td>Name :</td>
									<td><input type="text" name="contactName"
										placeholder="enter name"></td>
								</tr>
								<tr>
									<td><button type="submit" class="btn btn-primary">view</button></td>
								</tr>
							</table>
						</form>
						</p>

						<div
							style="height: 8rem; overflow: scroll; background-color: white; background-color: white; box-shadow: 0px 0px 10px black; padding-left: 10px;">
							<%
								if (request.getAttribute("contactName") != null) {
									String userName = session.getAttribute("userName").toString();
									String contactName = request.getAttribute("contactName").toString();
									DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
									Query viewContact = new Query(userName)
											.setFilter(new FilterPredicate("contactName", FilterOperator.EQUAL, contactName));
									PreparedQuery viewQuery = datastore.prepare(viewContact);
									Entity view = viewQuery.asSingleEntity();
									if (view != null) {
										out.println("<b>Name :</b>" + view.getProperty("contactName") + "<br>");
										out.println("<b>email : </b>" + view.getProperty("email") + "<br>");
										out.println("<b>company : </b>" + view.getProperty("company") + "<br>");
										out.println("<b>location </b>: " + view.getProperty("location") + "<br>");
										out.println("<b>Number : </b>");
										List<String> viewList = (List) view.getProperty("list");
										Iterator<String> iterate = viewList.iterator();
										while (iterate.hasNext()) {
											out.println(" : " + iterate.next());
										}
										out.println("<br>");
									} else {
										out.println("no such contact exist");
									}

								}
							%>
						</div>

					</div>
				</div>
			</div>
			<div class="col-lg-3">

				<div class="card"
					style="width: 19rem; box-shadow: 10px 20px 30px black;">

					<div class="card-body" style="background-color: #d4e1ff;">
						<h5 class="card-title">Add new contact</h5>
						<p class="card-text">
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
									<td><Button type="submit" class="btn btn-primary">Add</Button></td>
								</tr>

							</table>
						</form>
						<br>
						<h5 class="card-title">Add only number</h5>
						<form method="post" action="AddNumber">
							<table>
								<tr>
									<td>Enter existing name :</td>
									<td><input type="text" name="contactName"
										placeholder="enter existing name"></td>
								</tr>

								<tr>
									<td>Enter new number :</td>
									<td><input type="text" name="phoneNumber"
										placeholder="enter new number"></td>
								</tr>
								<tr>
									<td><Button type="submit" class="btn btn-primary">Add
											Number</Button></td>
								</tr>
							</table>
						</form>
						</p>

					</div>
				</div>
			</div>
			<div class="col-lg-3">
				<div class="card"
					style="width: 19rem; box-shadow: 10px 20px 30px black;">
					<div class="card-body" style="background-color: #d4e1ff;">
						<h5 class="card-title">Update contact</h5>
						<p class="card-text">
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
									<td><Button type="submit" class="btn btn-primary">Update</Button></td>
								</tr>
							</table>
						</form>
						<br>
						<h5 class="card-title">Update only number</h5>
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
									<td><Button type="submit" class="btn btn-primary">Update
											Number</Button></td>
								</tr>
							</table>
						</form>
						</p>

					</div>
				</div>
			</div>
			<div class="col-lg-3">
				<div class="card"
					style="width: 19rem; box-shadow: 10px 20px 30px black;">

					<div class="card-body" style="background-color: #d4e1ff;">
						<h5 class="card-title">Delete contact</h5>
						<p class="card-text">
						<form method="post" action="DeleteContact">
							<table>
								<tr>
									<td>Enter existing Name :</td>
									<td><input type="text" name="contactName"
										placeholder="Enter name"></td>
								</tr>
								<tr>
									<td><Button type="submit" class="btn btn-primary">Delete</Button></td>
								</tr>
							</table>
						</form>
						<br>
						<h5 class="card-title">Delete only number</h5>
						<form method="post" action="DeleteNumber">
							<table>
								<tr>
									<td>Enter existing name :</td>
									<td><input type="text" name="contactName"
										placeholder="enter existing name"></td>
								</tr>
								<tr>
									<td>Enter existing number :</td>
									<td><input type="text" name="phoneNumber"
										placeholder="enter existing number"></td>
								</tr>
								<tr>
									<td><Button type="submit" class="btn btn-primary">Delete
											Number</Button></td>
								</tr>
							</table>
						</form>
						</p>

					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<%@ page import="java.util.*"%>
			<%@ page import="com.google.appengine.api.datastore.*"%>
			<%@ page import="com.google.appengine.api.datastore.Query.*"%>
			<div
				style="color: red; font-size: 25px; text-shadow: 0px 0px 5px white;">
				<%
					if (request.getAttribute("message") != null) {
						out.println(request.getAttribute("message"));
					}
				%>
			</div>



			<div
				style="padding-left: 15%; padding-top: 5%; padding-right: 15%; background-color: #d4e1ff; box-shadow: 0px 0px 20px black;">

				<div align="center"
					style="font-size: 40px; text-shadow: 0px 0px 10px black;">CONTACTS</div>
				<div align="center"
					style="box-shadow: 0px 0px 20px black; overflow: scroll; background-color: white; padding-top: 10px;]">


					<%
						if (session.getAttribute("userName") != null) {
							DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
							Query query = new Query(session.getAttribute("userName").toString());
							PreparedQuery preparedQuery = datastore.prepare(query);
							out.println("<table>");
							for (Entity entity : preparedQuery.asIterable()) {
								out.println("<tr><td><b>");
								out.println(entity.getProperty("contactName"));
								out.println("</b></td>");
								List<String> numberList = (List) entity.getProperty("list");
								try {
									Iterator<String> iterate = numberList.iterator();
									while (iterate.hasNext()) {
										out.println("<td>");
										out.println(" : " + iterate.next());
										out.println("<br>");
									}
									out.println("</tr>");
								} catch (Exception e) {
									out.println("<td>");
									out.println(" no number");
									out.println("</td>");
									out.println("<br>");
								}

								out.println("</tr>");
							}
						} else
					%>
				</div>
			</div>


		</div>

	</div>
</body>
</html>