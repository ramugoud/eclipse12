<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <c:if test="${not empty orderid}">
 <c:out value="${orderid.orderId}"></c:out>  successfully place your order <br>
 <a href="vieworders">view orders</a>
 </c:if>
<table border=2>
<c:if test="${not empty order}">
<c:forEach var="order" items="${order}">
  	<tr><td><c:out value="${order.orderId}"></c:out></td>
	<td><c:out value="${order.total}"></c:out></td>
	<td><c:out value="${order.uname}"></c:out></td>    
	<td><c:out value="${order.status}"></c:out></td><td><a href="decision?order=${order.orderId}&msg=1">accept</a>&nbsp;&nbsp;&nbsp;<a href="decision?order=${order.orderId}&msg=2">Reject</a></td>
    	
        <c:forEach var="orderitem" items="${order.orderItems}">
           <tr><td><c:out value="${orderitem.itemId}"></c:out></td>
                <td><c:out value="${orderitem.name}"></c:out></td>
                <td><c:out value="${orderitem.price}"></c:out></td>
        </c:forEach>
        </c:forEach>  
 </c:if>
  <c:if test="${empty order}">
     <h5>No Pending Orders</h5> 
  </c:if>      
</table>
<br>
<br>
<table border=2>
<c:forEach var="accept" items="${acceptorders}">
  	<tr><td><c:out value="${accept.orderId}"></c:out></td>
	<td><c:out value="${accept.total}"></c:out></td>
	<td><c:out value="${accept.uname}"></c:out></td>
	<td><c:out value="${accept.status}"></c:out></td>
        <c:forEach var="orderitem" items="${accept.orderItems}">
           <tr><td><c:out value="${orderitem.itemId}"></c:out></td>
                <td><c:out value="${orderitem.name}"></c:out></td>
                <td><c:out value="${orderitem.price}"></c:out></td>
        </c:forEach>  
  
    </c:forEach>
</table>
<table border=2>
<c:forEach var="reject" items="${rejectorders}">
  	<tr><td><c:out value="${reject.orderId}"></c:out></td>
	<td><c:out value="${reject.total}"></c:out></td>
    <td><c:out value="${reject.uname}"></c:out></td>
	<td><c:out value="${reject.status}"></c:out></td>

        <c:forEach var="orderitem" items="${reject.orderItems}">
           <tr><td><c:out value="${orderitem.itemId}"></c:out></td>
                <td><c:out value="${orderitem.name}"></c:out></td>
                <td><c:out value="${orderitem.price}"></c:out></td>
        </c:forEach>  
  
    </c:forEach>
</table>
</body>
</html>