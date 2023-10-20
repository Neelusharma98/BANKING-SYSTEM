<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background-image: url("bankimages.jpeg");
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }

        .container {
            margin-top: 5rem;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-form {
            max-width: 400px;
            width: 100%;
            padding: 2rem;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="login-form">
            <h2>Login</h2>
            <form action="LoginController" method="POST">
                <input type="hidden" name="data" value="data">
                <div class="form-group">
                    <label for="role">Role:</label>
                    <select class="form-control" id="role" name="role" required>
                        <option value="Admin">Admin</option>
                        <option value="Customer">Customer</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="password" name="password" required>
                        <div class="input-group-append">
                            <span class="input-group-text" id="togglePassword">
                                <i class="fas fa-eye-slash" aria-hidden="true"></i>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <button type="reset" class="btn btn-secondary">Cancel</button>
                </div>
            </form>
            <p id="error-message" class="text-danger"></p>
            <p>Don't have an account? <a href="AdminRegistration.jsp">Register here</a></p>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/js/all.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const passwordField = document.getElementById("password");
            const togglePassword = document.getElementById("togglePassword");

            togglePassword.addEventListener("click", function () {
                if (passwordField.type === "password") {
                    passwordField.type = "text";
                    togglePassword.innerHTML = '<i class="fas fa-eye" aria-hidden="true"></i>';
                } else {
                    passwordField.type = "password";
                    togglePassword.innerHTML = '<i class="fas fa-eye-slash" aria-hidden="true"></i>';
                }
            });
        });
    </script>
</body>
</html>