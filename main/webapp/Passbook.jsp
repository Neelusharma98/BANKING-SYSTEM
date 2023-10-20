<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Passbook</title>
    <!-- Add Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add your custom styles here -->
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Passbook</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Transaction Type</th>
                    <th>Amount</th>
                    <th>Balance</th>
                    <th>Transaction Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="transaction" items="${transactions}">
                    <tr>
                        <td>${transaction.transactionid}</td>
                        <td>${transaction.type}</td>
                        <td>${transaction.balance}</td>
                        <td>${transaction.transactionDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="CustomerHome.jsp" class="btn btn-primary">Back to Dashboard</a>
    </div>

    <!-- Add Bootstrap JS and jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
