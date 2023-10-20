<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Bank Account for Customer</title>
    <!-- Add Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-image: url("bank.jpeg"); 
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            padding-top: 20px;
            margin: 0;
            height: 100vh; /* Ensure full viewport height */
        }
        .container {
            margin-top: 20px;
        }
        .bank-image {
            max-width: 100%;
            height: auto;
        }
        .card {
            border: none;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .card-header {
            background-color: #007bff;
            color: #fff;
        }
        .form-container {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h1 class="text-center">Add Bank Account for Customer</h1>
            </div>
            <div class="card-body">
                <form action="AddAccount" method="POST">
                    <div class="form-group">
                        <label for="customerId">Customer ID:</label>
                        <input type="text" class="form-control" id="customerId" name="customerId" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="balance">Balance (Minimum 1000):</label>
                        <input type="number" class="form-control" id="balance" name="balance" required min="1000">
                    </div>
                    <button type="submit" class="btn btn-primary">Add </button>
                    <!-- Cancel button that redirects to HomeAdmin.jsp -->
                    <a href="HomeAdmin.jsp" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>

    <!-- Add Bootstrap JS and jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
