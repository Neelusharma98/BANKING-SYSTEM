package com.techlabs.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/AddAccount")
public class AddAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the values from the form
        String customerId = request.getParameter("customerId");
        String balance = request.getParameter("balance");

        // Check if customerId exists in the CustomerDetail table
        if (customerExists(customerId)) {
            // Check if the account already exists for the given customerId
            if (!accountExists(customerId)) {
                // Attempt to add the bank account
                if (addBankAccount(customerId, balance)) {
                    // Successfully added bank account, redirect to a success page
                    response.sendRedirect("SuccessfulAccountAdded.jsp");
                } else {
                    // Failed to add bank account, redirect to an unsuccessful page
                    response.sendRedirect("UnsuccessfulAccountAdded.jsp");
                }
            } else {
                // Account already exists for the given customer, display an error message
                request.setAttribute("errorMessage", "Account already exists for the given Customer ID.");
                request.getRequestDispatcher("UnsuccessfulAccountAdded1.jsp").forward(request, response);
            }
        } else {
            // If the customer does not exist, redirect to an unsuccessful page
            response.sendRedirect("UnsuccessfulAccountAdded.jsp");
        }
    }
    
    
    private boolean customerExists(String customerId) {
        boolean exists = false;
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM CustomerDetail WHERE customerid = ?");
            statement.setString(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            exists = resultSet.next(); // If resultSet.next() is true, customer exists
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    private boolean addBankAccount(String customerId, String balance) {
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO BankAccount (customerId, balance) VALUES (?, ?)"
            );
            statement.setString(1, customerId);
            statement.setString(2, balance);

            // Attempt to insert the row, and check if it was successful
            int rowsInserted = statement.executeUpdate();

            statement.close();
            connection.close();

            // If one row was inserted, it was successful
            return rowsInserted == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Failed to insert the row
        }
    }

    private boolean accountExists(String customerId) {
        boolean exists = false;
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM BankAccount WHERE customerId = ?");
            statement.setString(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            exists = resultSet.next(); // If resultSet.next() is true, account exists
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

}
