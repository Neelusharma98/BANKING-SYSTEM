<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Registration</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Custom CSS for Background Image -->
    <style>
        body {
            background-image: url('bank.jpeg'); 
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center;
        }
        .container {
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 10px;
            width:40%;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h2>Admin Registration</h2>
    <form id="registrationForm" action="RegController" method="post">
        <div class="form-group">
            <label for="role">Role:</label>
            <input type="text" class="form-control" id="role" name="role" value="Admin" readonly>
        </div>
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" class="form-control" id="firstName" name="firstName" required>
            <small class="text-danger" id="firstNameError"></small>
        </div>
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" class="form-control" id="lastName" name="lastName" required>
            <small class="text-danger" id="lastNameError"></small>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" required>
            <small class="text-danger" id="emailError"></small>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required>
            <small class="text-danger" id="passwordError"></small>
        </div>
        <div class="form-group">
            <label for="mobile">Mobile Number:</label>
            <input type="tel" class="form-control" id="mobile" name="mobile" required>
            <small class="text-danger" id="mobileError"></small>
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
        <button type="cancel" class="btn btn-primary">cancel</button>
    </form>
</div>

<!-- Include Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- Client-side Validation using JavaScript -->
<script>
    document.getElementById("registrationForm").addEventListener("submit", function(event) {
        let isValid = true;
        const firstName = document.getElementById("firstName");
        const lastName = document.getElementById("lastName");
        const email = document.getElementById("email");
        const password = document.getElementById("password");
        const mobile = document.getElementById("mobile");

        // Validate First Name
        if (firstName.value.trim() === "") {
            document.getElementById("firstNameError").innerText = "Please enter First Name";
            isValid = false;
        } else {
            document.getElementById("firstNameError").innerText = "";
        }

        // Validate Last Name
        if (lastName.value.trim() === "") {
            document.getElementById("lastNameError").innerText = "Please enter Last Name";
            isValid = false;
        } else {
            document.getElementById("lastNameError").innerText = "";
        }

        // Validate Email
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email.value.trim())) {
            document.getElementById("emailError").innerText = "Please enter a valid Email";
            isValid = false;
        } else {
            document.getElementById("emailError").innerText = "";
        }

        // Validate Password
        if (password.value.length < 6) {
            document.getElementById("passwordError").innerText = "Password must be at least 6 characters long";
            isValid = false;
        } else {
            document.getElementById("passwordError").innerText = "";
        }

        // Validate Mobile Number
        const mobileRegex = /^\d{10}$/;
        if (!mobileRegex.test(mobile.value.trim())) {
            document.getElementById("mobileError").innerText = "Please enter a valid 10-digit Mobile Number";
            isValid = false;
        } else {
            document.getElementById("mobileError").innerText = "";
        }

        if (!isValid) {
            event.preventDefault(); // Prevent form submission if validation fails
        }
    });
</script>

</body>
</html>
    