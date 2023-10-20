<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Transactions</title>
    <!-- Add Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-image: url('bank.jpeg');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center center;
            padding-top: 20px;
            margin: 0;
            height: 100%;
        }
        .container {
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 10px;
            padding: 80px;
            height: 70vh;
            width:80vh;
        }
        .table-container {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center">View Transactions</h1>
        <div class="table-container">
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Account Number</th>
                        <th>Balance</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${transactions}" var="transaction">
                        <tr>
                            <td>${transaction.accountNumber}</td>
                            <td>${transaction.balance}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <a href="HomeAdmin.jsp" class="btn btn-primary btn-block">Back to Dashboard</a>
    </div>

    <!-- Add Bootstrap JS and jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
    