package com.techlabs.controllers;




import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PassbookServlet")
public class PassbookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int customerAccountNumber = (int) session.getAttribute("CustomerAccountNumber");

        List<NewTransaction> transactions = new ArrayList<>();

        try {
            // Establish a database connection
            Connection connection = DbConnection.getConnection();

            // Retrieve the customer's transaction history
            transactions = getTransactionHistory(connection, customerAccountNumber);

            // Close the database connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Forward the transaction data to the JSP page for rendering
        request.setAttribute("transactions", transactions);
        request.getRequestDispatcher("Passbook.jsp").forward(request, response);
    }

    private List<NewTransaction> getTransactionHistory(Connection connection, int customerAccountNumber)
            throws SQLException {
        List<NewTransaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM TransactionDetail WHERE accountNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerAccountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int transactionId = resultSet.getInt("transactionid");
                int receiverAccountNumber = resultSet.getInt("receiverAccountNumber");
                String transactionType = resultSet.getString("type");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                java.sql.Timestamp transactionDate = resultSet.getTimestamp("transactionDate");

                NewTransaction transaction = new NewTransaction(
                    transactionId, customerAccountNumber, receiverAccountNumber,
                    transactionType, balance, transactionDate
                );

                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
