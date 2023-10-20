<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transaction Form</title>
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
            width: 700px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 15px;
        }

        /* Style for Submit button */
        .btn-primary {
            background-color: #007bff;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        // Function to show/hide receiver account number field based on the transaction type
        function toggleAccountFields() {
            var transactionType = document.getElementById("transactionType").value;
            var senderAccountDiv = document.getElementById("senderAccountDiv");
            var receiverAccountDiv = document.getElementById("receiverAccountDiv");

            if (transactionType === "transfer") {
                //senderAccountDiv.style.display = "block";
                receiverAccountDiv.style.display = "block";
            } else {
                senderAccountDiv.style.display = "none";
                receiverAccountDiv.style.display = "none";
            }
        }

        // Function to validate the minimum amount
        function validateAmount() {
            var amount = parseFloat(document.getElementById("amount").value);
            if (isNaN(amount) || amount < 500) {
                alert("Minimum amount is 500.");
                return false;
            }
            return true;
        }
        function refreshPage() {
            location.reload();
        }
    </script>
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Transaction Details</h1>
        <form action="TransactionControllerServlet2" method="POST" onsubmit="return validateAmount()">
            <div class="form-group">
                <label for="transactionType">Transaction Type:</label>
                <select class="form-control" id="transactionType" name="transactionType" onchange="toggleAccountFields()">
                    
                 <option value="debit">Debit</option>
                 <option value="withdraw">Withdraw</option> 
                 <option value="transfer">Transfer</option>

                    
                </select>
            </div>
            
            <div class="form-group" id="receiverAccountDiv" style="display: none;">
                <label for="receiverAccountNumber">Receiver's Account Number:</label>
                <input type="text" class="form-control" id="receiverAccountNumber" name="receiverAccountNumber" required>
            </div>
            <div class="form-group">
                <label for="customerAccountNumber"> Account Number(Sender):</label>
                <input type="text" class="form-control" id="customerAccountNumber" placeholder="Enter your account number" name="customerAccountNumber" required>
            </div>
            <div class="form-group">
                <label for="amount">Amount (Minimum 500):</label>
                <input type="number" step="0.01" class="form-control" id="amount" name="amount" required>
            </div>
            <!--  <div class="form-group">
                <label for="description">Description:</label>
                <input type="text" class="form-control" id="description" name="description" required>
            </div>-->
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="button" class="btn btn-secondary" onclick="refreshPage()">Cancel</button>
        </form>
    </div>

    <!-- Add Bootstrap JS and jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
