<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Successful</title>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   
    <style>
        body {
            background-image: url("bank.jpeg");
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            
            
            
        }
        .container h2 {
            color: #000;
        }
        .container p {
            color: #000;
        }
        .container a {
            color: #007bff;
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Registration Successful</h2>
    <p>Admin has been registered successfully</p>
    <p>You can now <a href="loginpage.jsp">log in</a> </p>
</div>

<!-- Include Bootstrap JS (Assuming you have Bootstrap installed) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
