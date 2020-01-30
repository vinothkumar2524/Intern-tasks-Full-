<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="javax.servlet.*" %>
<%
response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
if(session.getAttribute("name")!=null)
{
	RequestDispatcher dispatch=request.getRequestDispatcher("DisplayContacts.jsp");
	dispatch.forward(request, response);
}
%>
<h1>Login</h1>
<form action="LoginServlet" method="post">  
<table>
<tr><td>Name :</td><td><input type="text" name="loginName"></td></tr>
<tr><td>Password :</td><td><input type="password" name="loginPassword"></td></tr>
<tr><td><Button type="submit">Login</Button>
</table>
</form> 
<h4>Don't have an account?</h4>
<button onclick="window.location.href = 'Register.jsp';">Register Now</button>
<%
    if(null!=request.getAttribute("message"))
    {
        out.println(request.getAttribute("message"));
    }
%> 
</body>
</html>