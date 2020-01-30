<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<%@ page import="javax.servlet.*"%>
	<%
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		if (session.getAttribute("userName") != null) {
			RequestDispatcher dispatch = request.getRequestDispatcher("DisplayContacts.jsp");
			dispatch.forward(request, response);
		}
	%>
<body style="margin: 0 auto; float: center;" >
<div align="center">
<div class="card" style="width: 20rem;box-shadow: 10px 20px 30px black;margin-top:10%;">
  
  <div class="card-body" style="background-color:#d4e1ff;">
    <h4 class="card-title">Login</h4>
    <p class="card-text"><form action="LoginServlet" method="post">

			<table id="table">
				<tr>
					<td><div id="label">Name :</div></td>
					<td><input id="input" type="text" name="loginName"></td>
				</tr>
				<tr>
					<td><div id="label">Password :</div></td>
					<td><input id="input" type="password" name="loginPassword"></td>
				</tr>
				
					
				
			</table>
			<br>
			<Button id="button" type="submit" class="btn btn-primary">Login</Button>

		</form></p>
		<h5>Don't have an account?</h5>
    <a class="btn btn-primary" href="Register.jsp" role="button">Register now</a>
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
</body>
</html>