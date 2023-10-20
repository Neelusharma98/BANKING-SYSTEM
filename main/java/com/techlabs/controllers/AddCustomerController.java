package com.techlabs.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddCustomerController")
public class AddCustomerController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String mobile = request.getParameter("mobile");

        // Validate the data (you can add more validation)
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || mobile.isEmpty()) {
            response.sendRedirect("UnsuccessfulCustomerAdded.jsp");
            return; // Exit early if any field is empty
        }

        // Check if the user already exists in the 'user' table
        if (userExists(email)) {
            response.sendRedirect("UnsuccessfulCustomerAdded.jsp");
        } else {
            // User doesn't exist so insert data into 'user' and 'CustomerDetail' tables
            if (insertUser(email, password, "Customer") && insertCustomer(firstName, lastName, email, password, mobile)) {
                response.sendRedirect("SuccessfulCustomerAdded.jsp");
            } else {
                response.sendRedirect("UnsuccessfulCustomerAdded.jsp");
            }
        }
    }

    // Check if a user with the given email already exists in the 'user' table
    private boolean userExists(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DbConnection.getConnection();
            String query = "SELECT * FROM user WHERE userid=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Insert a new user into the 'user' table
    private boolean insertUser(String email, String password, String role) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DbConnection.getConnection();
            String query = "INSERT INTO user (userid, password, role) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Insert customer data into the 'CustomerDetail' table
    private boolean insertCustomer(String firstName, String lastName, String email, String password, String mobile) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DbConnection.getConnection();
            String query = "INSERT INTO CustomerDetail (firstName, lastName, email, password, mobile) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, mobile);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
