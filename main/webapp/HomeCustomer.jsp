<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Home</title>
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
            
        }
        .container {
            margin-top: 80px;
            
        }
        .card {
            border: none;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .card-header {
            background-color: #007bff;
            color: #fff;
        }
        .btn-container {
            margin-top: 20px;
        }
        .btn {
            margin: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <!-- Customer Home -->
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h1 class="text-center">Customer Dashboard</h1>
                        <!--  <h1>Welcome, <%= session.getAttribute("Username") %></h1>-->
                    </div>
                    <div class="card-body">
                        <div class="btn-container text-center">
                            <!-- Passbook Section -->
                            <a href="PassbookServlet" class="btn btn-primary">Passbook</a>
                            <!-- New Transaction Section -->
                            <a href="NewTransactionform.jsp" class="btn btn-primary">New Transaction</a>
                            <!-- View Profile Section -->
                            <a href="EditProfile.jsp" class="btn btn-primary">Edit Profile</a>
                        </div>
                        
                        
                        <a href="loginpage.jsp" class="class="btn btn-primary">Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Add Bootstrap JS and jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
    