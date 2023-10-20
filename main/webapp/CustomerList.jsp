<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer List</title>
</head>
<body>
    <h1>Customer List</h1>
    
    <!-- Search Bar -->
    <form action="CustomerViewController" method="POST">
        <input type="text" name="searchValue" placeholder="Search by Name">
        <input type="submit" value="Search">
    </form>
    
    <table border="1">
        <tr>
            <th>Customer ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Mobile</th>
        </tr>
        <c:forEach var="customer" items="${customers}">
            <tr>
                <!--  <td>${customer.customerid}</td>-->
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.email}</td>
                <td>${customer.mobile}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
    