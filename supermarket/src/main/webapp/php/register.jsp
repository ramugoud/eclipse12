<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>

<div style="color: red">${err}</div>

<form method="post" action="register">
<input type="text" placeholder="First Name" name="fname" value="${param.fname}" required><br>
<input type="text" placeholder="Last Name" name="lname" value="${param.lname}"><br>
<input type="email" placeholder="email" name="email" value="${param.email}" required="required"><br>
<input type="tel" placeholder="Phone number" name="phone" value="${param.phone}"><br>
<input type="text" placeholder="User Name" name="un"value="${param.un}"><br>
<input type="password" placeholder="Password" name="pw"><br>
<br>
<input type="submit" value="Signup">



</form>
<a href="login">login</a>



</body>
</html>