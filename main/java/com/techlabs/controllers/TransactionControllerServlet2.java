package com.techlabs.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TransactionControllerServlet2")
public class TransactionControllerServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String transactionType = request.getParameter("transactionType");
        String customerAccountNumber = request.getParameter("customerAccountNumber");
        String receiverAccountNumber = null;
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));

        if ("transfer".equals(transactionType)) {
            receiverAccountNumber = request.getParameter("receiverAccountNumber");
        }

        boolean success = performTransaction(transactionType, customerAccountNumber, receiverAccountNumber, amount);

        if (success) {
            response.sendRedirect("TransactionSuccess.jsp");
        } else {
            response.sendRedirect("TransactionError.jsp");
        }
    }
    
    
    private boolean performTransaction(String transactionType, String customerAccountNumber,String receiverAccountNumber, BigDecimal amount) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean success = false;

        try {
            connection = DbConnection.getConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
                BigDecimal currentBalance = getCurrentBalance(connection, customerAccountNumber);

                if ("debit".equals(transactionType)) {
                    BigDecimal newBalance = add(currentBalance, amount);
                    updateAndInsert(connection, customerAccountNumber, null, transactionType, amount, newBalance);
                    success = true;
                } else if ("withdraw".equals(transactionType)) {
                    BigDecimal newBalance = subtract(currentBalance, amount);
                    if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
                        updateAndInsert(connection, customerAccountNumber, null, transactionType, amount, newBalance);
                        success = true;
                    }
                } else if ("transfer".equals(transactionType)) {
                    BigDecimal receiverBalance = getCurrentBalance(connection, receiverAccountNumber);
                    BigDecimal newSenderBalance = subtract(currentBalance, amount);

                    if (newSenderBalance.compareTo(BigDecimal.ZERO) >= 0 && accountExists(connection, receiverAccountNumber)) {
                        updateAndInsert(connection, customerAccountNumber, receiverAccountNumber, transactionType, amount,
                                newSenderBalance);
                        updateAccountBalance(connection, receiverAccountNumber, add(receiverBalance, amount));
                        success = true;
                    }
                }

                if (success) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
            } else {
                System.err.println("Database connection is null.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }


    private BigDecimal add(BigDecimal num1, BigDecimal num2) {
        return num1.add(num2);
    }

    private BigDecimal subtract(BigDecimal num1, BigDecimal num2) {
        return num1.subtract(num2);
    }

    private void updateAndInsert(Connection connection, String senderAccountNumber, String receiverAccountNumber,
            String transactionType, BigDecimal amount, BigDecimal newBalance) throws SQLException {
        updateAccountBalance(connection, senderAccountNumber, newBalance);
        insertTransaction(connection, senderAccountNumber, receiverAccountNumber, transactionType, amount);
    }

    private BigDecimal getCurrentBalance(Connection connection, String accountNumber) throws SQLException {
        String query = "SELECT balance FROM BankAccount WHERE accountNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, accountNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBigDecimal("balance");
                }
            }
        }
        return BigDecimal.ZERO;
    }

    private boolean accountExists(Connection connection, String accountNumber) throws SQLException {
        String query = "SELECT COUNT(*) as count FROM BankAccount WHERE accountNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, accountNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        }
        return false;
    }

    private void updateAccountBalance(Connection connection, String accountNumber, BigDecimal newBalance)
            throws SQLException {
        String query = "UPDATE BankAccount SET balance = ? WHERE accountNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBigDecimal(1, newBalance);
            preparedStatement.setString(2, accountNumber);
            preparedStatement.executeUpdate();
        }
    }

    private void insertTransaction(Connection connection, String senderAccountNumber, String receiverAccountNumber,
            String transactionType, BigDecimal amount) throws SQLException {
        String query = "INSERT INTO transaction_detail (account_number, receiver_account_number, type, amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, senderAccountNumber);
            preparedStatement.setString(2, receiverAccountNumber);
            preparedStatement.setString(3, transactionType);
            preparedStatement.setBigDecimal(4, amount);
            preparedStatement.executeUpdate();
        }
    }

}