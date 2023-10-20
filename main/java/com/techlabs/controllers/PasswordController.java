package com.techlabs.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PasswordController
 */
@WebServlet("/PasswordController")
public class PasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession();
        
        // Get the new password from the form
        String newPassword = request.getParameter("password");
        
        // Get the customer's email from the session
        String customerUsername = (String) session.getAttribute("Username");
        
        if (customerUsername == null) {
            // Handle the case where customerEmail is not found in the session
            response.sendRedirect("Login.jsp");
            return;
        }

        // Update the password in the CustomerDetail table
        String updateCustomerPasswordQuery = "UPDATE CustomerDetail SET password = ? WHERE email = ?";

        // Update the password in the user table
        String updateUserPasswordQuery = "UPDATE user SET password = ? WHERE userid = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement customerStatement = connection.prepareStatement(updateCustomerPasswordQuery);
             PreparedStatement userStatement = connection.prepareStatement(updateUserPasswordQuery)) {

            // Set the new password and customer email in both queries
            customerStatement.setString(1, newPassword);
            customerStatement.setString(2, customerUsername);

            userStatement.setString(1, newPassword);
            userStatement.setString(2, customerUsername);

            // Execute the updates
            int customerUpdateCount = customerStatement.executeUpdate();
            int userUpdateCount = userStatement.executeUpdate();

            // Check if both updates were successful
            if (customerUpdateCount > 0 && userUpdateCount > 0) {
                // Password change successful
                session.setAttribute("passwordChangeSuccess", true);
                response.sendRedirect("PasswordChangeSuccess.jsp");
            } else {
                // Password change failed
                session.setAttribute("passwordChangeSuccess", false);
                response.sendRedirect("PasswordChangeFailure.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors here
            session.setAttribute("passwordChangeSuccess", false);
            response.sendRedirect("PasswordChangeFailure.jsp");
        }
    }

	

}
