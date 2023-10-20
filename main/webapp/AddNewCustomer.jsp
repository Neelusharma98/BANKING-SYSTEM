<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Customer</title>
    <!-- Add Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
           
            background-image: url("bank.jpeg");
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            margin: 0;
            padding: 0;
        }
        h1 {
            
            text-align: center;
            margin-top: 20px;
        }
        form {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 8px;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="tel"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"],
        input[type="Cancel"] ,
        input[type="button"] {
            display: inline-block;
            padding: 10px 20px;
            background-color: blue;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            margin-right: 10px;
        }
        
        .error-message {
            color: red;
        }
         
}
    </style>
    <script>
        function validateNameInput(inputId) {
            var inputField = document.getElementById(inputId);
            var inputValue = inputField.value.trim();
            var firstChar = inputValue.charAt(0);

            if (firstChar !== firstChar.toUpperCase()) {
                alert("PLEASE ENTER " + inputId + " WITH AN UPPERCASE LETTER.");
                inputField.focus();
                return false;
            }
            return true;
        }

        function validateForm() {
            return validateNameInput("firstName") && validateNameInput("lastName");
        }
    </script>





    
    
    
</head>
<body>
    
    <h1 style="color: white;">Add New Customer</h1>
    
    <form action="AddCustomerController" method="post" onsubmit="return validateForm();">
        
        <label for="firstName">Customer First Name:</label>
        <input type="text" id="firstName" name="firstName" required><br>
        
        <label for="lastName">Customer Last Name:</label>
        <input type="text" id="lastName" name="lastName" required><br>
   
       

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>
        <span class="error-message">Please enter a valid email address.</span><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        <span class="error-message">Password must be at least 6 characters long.</span><br>

        <label for="mobile">Mobile Number:</label>
        <input type="tel" id="mobile" name="mobile" pattern="[0-9]{10}" required><br>
        <span class="error-message">Please enter a valid 10-digit mobile number.</span><br>

        <input type="submit" value="Submit">
        <input type="button" value="Cancel" onclick="window.location.href='HomeAdmin.jsp';">
        
    </form>

    <!-- Add Bootstrap JS and jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
    