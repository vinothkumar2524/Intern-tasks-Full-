<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ContactRegistration</title>
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
if(session.getAttribute("name")!=null)
{
	response.sendRedirect("DisplayContacts.jsp");
}
%>
<h1>Register</h1>
<form action="RegisterServlet" method="post">  
<table>
<tr><td>Name :</td><td><input type="text" name="registerName"></td></tr>
<tr><td>Password :</td><td><input type="password" name="registerPassword"></td></tr>
<tr><td>RE-Enter Password :</td><td><input type="password" name="registerRePassword"></td></tr>

<tr><td><Button type="submit">Register</Button>
</table>
</form> 
<h3>Already have an account?</h3>
<button onclick="window.location.href = 'Login.jsp';">Login Now</button> 
<%
    if(null!=request.getAttribute("errorMessage"))
    {
        out.println(request.getAttribute("errorMessage"));
    }

%>
</body>
</html>