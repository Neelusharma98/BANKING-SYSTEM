package com.techlabs.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/ViewTransactionsServlet")
public class ViewTransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve transaction data from the database
        List<Transaction> transactions = getTransactionsFromDatabase();

        // Set the transactions data as an attribute to pass to the JSP
        request.setAttribute("transactions", transactions);

        // Forward the request to the JSP for rendering
        //request.getRequestDispatcher("ViewTransactions1.jsp").forward(request, response);
        request.getRequestDispatcher("ViewTransaction1.jsp").forward(request, response);
        
    }

    // Method to fetch transaction data from the database
    private List<Transaction> getTransactionsFromDatabase() {
        List<Transaction> transactions = new ArrayList<>();
        // You should implement the database connection and query here
        // Retrieve transaction data from the BankAccount table and populate the 'transactions' list
        // Example code for database connection and query:
        try {
            Connection connection = DbConnection.getConnection();
            String query = "SELECT * FROM BankAccount";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String accountNumber = resultSet.getString("accountNumber");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                // Create a Transaction object and add it to the 'transactions' list
                transactions.add(new Transaction(accountNumber, balance));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
