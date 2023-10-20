package com.techlabs.controllers;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/TransactionControllerServlet")
public class TransactionControllerServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String transactionType = request.getParameter("transactionType");
        int customerAccountNumber = Integer.parseInt(request.getParameter("customerAccountNumber"));
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        int receiverAccountNumber = 0;

        if ("transfer".equals(transactionType)) {
            receiverAccountNumber = Integer.parseInt(request.getParameter("receiverAccountNumber"));
        }

        try {
            // Establish a database connection
        	Connection connection = DbConnection.getConnection();

            // Perform the selected transaction and update the balance
            BigDecimal newBalance = performTransaction(connection, transactionType, customerAccountNumber, amount, receiverAccountNumber);

            // Close the database connection
            connection.close();

            // Display a response to the user
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Transaction Successful</h2>");
            out.println("<p>New Balance: " + newBalance + "</p>");
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private BigDecimal performTransaction(Connection connection, String transactionType, int accountNumber, BigDecimal amount, int receiverAccountNumber)
            throws SQLException {
        BigDecimal newBalance = BigDecimal.ZERO;

        // Get the current balance
        BigDecimal currentBalance = getCurrentBalance(connection, accountNumber);

        // Perform the selected transaction and calculate the new balance
        switch (transactionType) {
            case "debit":
                newBalance = currentBalance.subtract(amount);
                break;
            case "withdraw":
                newBalance = currentBalance.subtract(amount);
                break;
            case "transfer":
                // Deduct from sender's account
                newBalance = currentBalance.subtract(amount);
                updateBalance(connection, accountNumber, newBalance);

                // Add to receiver's account
                BigDecimal receiverCurrentBalance = getCurrentBalance(connection, receiverAccountNumber);
                BigDecimal receiverNewBalance = receiverCurrentBalance.add(amount);
                updateBalance(connection, receiverAccountNumber, receiverNewBalance);
                break;
        }

        // Insert the transaction record into the database
        insertTransactionRecord(connection, transactionType, accountNumber, receiverAccountNumber, amount, newBalance);

        return newBalance;
    }

    private BigDecimal getCurrentBalance(Connection connection, int accountNumber) throws SQLException {
        String query = "SELECT balance FROM BankAccount WHERE accountNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBigDecimal("balance");
            }
        }
        return BigDecimal.ZERO;
    }

    private void updateBalance(Connection connection, int accountNumber, BigDecimal newBalance) throws SQLException {
        String query = "UPDATE BankAccount SET balance = ? WHERE accountNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBigDecimal(1, newBalance);
            preparedStatement.setInt(2, accountNumber);
            preparedStatement.executeUpdate();
        }
    }

    private void insertTransactionRecord(Connection connection, String transactionType, int accountNumber, int receiverAccountNumber, BigDecimal amount, BigDecimal newBalance)
            throws SQLException {
        String query = "INSERT INTO TransactionDetail (accountNumber, receiverAccountNumber, type, balance, transactionDate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        	preparedStatement.setInt(1, accountNumber);
            preparedStatement.setInt(2, receiverAccountNumber);
            preparedStatement.setString(3, transactionType);
            preparedStatement.setBigDecimal(4, amount);
            preparedStatement.setBigDecimal(5, newBalance);
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        }
    }


}
