package com.techlabs.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/RegController")
public class RegController extends HttpServlet {
		private static final long serialVersionUID = 1L;

	    

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();

	        String role = request.getParameter("role");
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        String mobile = request.getParameter("mobile");

	        Connection connection = null;
	        PreparedStatement checkCustomerStatement = null;
	        PreparedStatement insertAdminDetailStatement = null;
	        PreparedStatement insertUserStatement = null;

	        try {
	            connection = DbConnection.getConnection();

	            // Check if a customer with the same email exists
	            String checkCustomerQuery = "SELECT * FROM user WHERE userid=?";
	            checkCustomerStatement = connection.prepareStatement(checkCustomerQuery);
	            checkCustomerStatement.setString(1, email);

	            ResultSet resultSet = checkCustomerStatement.executeQuery();
	            if (resultSet.next()) {
	                //out.println("A customer with the same email already exists. Registration failed.");
	                response.sendRedirect("unsuccessreg.jsp");
	            } else {
	                // Insert data into AdminDetail
	                String insertAdminDetailQuery = "INSERT INTO AdminDetail (role, firstName, lastName, email, password, mobile) VALUES (?, ?, ?, ?, ?, ?)";
	                insertAdminDetailStatement = connection.prepareStatement(insertAdminDetailQuery);
	                insertAdminDetailStatement.setString(1, role);
	                insertAdminDetailStatement.setString(2, firstName);
	                insertAdminDetailStatement.setString(3, lastName);
	                insertAdminDetailStatement.setString(4, email);
	                insertAdminDetailStatement.setString(5, password);
	                insertAdminDetailStatement.setString(6, mobile);

	                int adminDetailResult = insertAdminDetailStatement.executeUpdate();

	                if (adminDetailResult > 0) {
	                    // Insert data into User table with role "Admin"
	                    String insertUserQuery = "INSERT INTO user (userid, password, role) VALUES (?, ?, ?)";
	                    insertUserStatement = connection.prepareStatement(insertUserQuery);
	                    insertUserStatement.setString(1, email);
	                    insertUserStatement.setString(2, password);
	                    insertUserStatement.setString(3, "Admin");

	                    int userResult = insertUserStatement.executeUpdate();

	                    if (userResult > 0) {
	                        out.println("Admin registration successful!");
	                        response.sendRedirect("successreg.jsp");
	                    } else {
	                    	
	                        out.println("Admin registration failed.");
	                    }
	                } else {
	                    out.println("Admin registration failed.");
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            out.println("Error: " + e.getMessage());
	        } finally {
	            try {
	                if (checkCustomerStatement != null) {
	                    checkCustomerStatement.close();
	                }
	                if (insertAdminDetailStatement != null) {
	                    insertAdminDetailStatement.close();
	                }
	                if (insertUserStatement != null) {
	                    insertUserStatement.close();
	                }
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
