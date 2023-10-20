<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
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
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            background-color: white; 
            width: 600px; 
            height: 350px; 
            padding: 20px; 
            border-radius: 10px; 
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
        }
    </style>
</head>

<body>
    <div class="container">
        <h2>Edit Password</h2>
        <form action="PasswordController" method="post" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="password">New Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="Update">
                <button type="button" class="btn btn-secondary" onclick="refreshPage()">Cancel</button>
            </div>
        </form>
        <div id="error-message" class="text-danger"></div>
        <a href="HomeCustomer.jsp">Back to Dashboard</a>
    </div>

    <!-- Add Bootstrap JS and jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <script>
        function validateForm() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var pattern = /^(?![_ ])(?!.*[_ ]{2})[A-Za-z0-9_ ]{6,}$/;

            if (password !== confirmPassword) {
                document.getElementById("error-message").innerText = "Passwords do not match!";
                return false;
            } else if (!pattern.test(password)) {
                document.getElementById("error-message").innerText = "Password must be at least 6 characters long(not contain spaces or underscores)";
                return false;
            }
            return true;
        }

        function refreshPage() {
            location.reload();
        }
    </script>
</body>
</html>
