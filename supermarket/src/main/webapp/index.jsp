<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Super Market</title>
</head>
<body>

welcome <c:if test="${not empty uid}">
<c:out value="${uid}"></c:out><a href="logout">Signout</a>
<a href="myaccount">My Profile</a>&nbsp;&nbsp;&nbsp;&nbsp;
 <a href="viewuserorders">My orders</a>
</c:if>
<c:if test="${empty uid}">
 <a href="login?cont=index">Signin</a> 
</c:if> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="checkout"><c:out value="${cart.size()}"></c:out></a>items(s) in 

<table border="2">
<c:forEach var="it" items="${products}" >
<tr><td><c:out value="${it.itemId}"></c:out></td>
<td><c:out value="${it.name}"></c:out></td>
<td><c:out value="${it.price}"></c:out></td>
<td><img src="data:image/jpg;base64,${it.base64Image}" width="240" height="300"/></td>
<td><a href="addCart?id=${it.itemId}"> Add to Cart</a></td>
</c:forEach>
</table>
</body>
</html>


<c:if test="${not empty uid}">
						<c:out value="${uid}"></c:out><a href="logout">Signout</a>
						</c:if>
						<c:if test="${empty uid}">
						 <a href="login?cont=index"><p >Signin</p></a> 
					</c:if>
					<a href="register">Register</a>