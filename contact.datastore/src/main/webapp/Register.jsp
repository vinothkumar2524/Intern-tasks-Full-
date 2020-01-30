<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<body>
	<%
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		if (session.getAttribute("userName") != null) {
			response.sendRedirect("DisplayContacts.jsp");
		}
	%>
	<div align="center">
	<div class="card" style="width: 25rem;box-shadow: 10px 20px 30px black;margin-top:10%;">
  
  <div class="card-body" style="background-color:#d4e1ff;">
    <h5 class="card-title">Register</h5>
    <p class="card-text"><form action="RegisterServlet" method="post">
			<table>
				<tr>
					<td><div id="label">Name :</div></td>
					<td><input type="text" name="registerName"></td>
				</tr>
				<tr>
					<td><div id="label">Password :</div></td>
					<td><input type="password" name="registerPassword"></td>
				</tr>
				<tr>
					<td><div id="label">RE-Enter Password :</div></td>
					<td><input type="password" name="registerRePassword"></td>
				</tr>
				<tr>
					<td><Button class="btn btn-primary" type="submit">Register</Button></td>
				</tr>
				
					
					
				
			</table>
		</form>
		<h5>Already have an account?</h5>
				
				
					<button class="btn btn-primary"
							onclick="window.location.href = 'Login.jsp';">Login Now</button>
		</p>
    
  </div>
</div>
	</div>
	
	<div align="center" style="color:red;font-size:20px;margin-top:20px;">
		<%
			if (null != request.getAttribute("message")) {
				out.println(request.getAttribute("message"));
			}
		%>
	</div>
	

</html>