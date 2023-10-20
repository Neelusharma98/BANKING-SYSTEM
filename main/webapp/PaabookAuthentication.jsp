<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
<
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
            background-image: url("bank.jpeg");
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
            max-width: 600px;
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
            <h2>Passbook Authentication</h2>
            <form action="PassbookController" method="POST">
                <div class="form-group">
                    <label for="accountNumber">Enter Your Account Number:</label>
                    <input type="text" class="form-control" id="accountNumber" name="accountNumber" required>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <button type="reset" class="btn btn-secondary">Cancel</button>
                </div>
                <p>back to <a href="HomeCustomer.jsp">dashboard</a>.</p>
            </form>
            <p id="message" class="text-info">Please enter your account number to view your transaction history and balance.</p>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/js/all.min.js"></script>
</body>
</html>
    