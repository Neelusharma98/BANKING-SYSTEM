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
import javax.servlet.http.HttpSession;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String role = request.getParameter("role");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            
            connection = DbConnection.getConnection();
            System.out.println("Connection established");

            String query = "SELECT * FROM user WHERE userid=? AND password=? AND role=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);

            resultSet = preparedStatement.executeQuery();
            HttpSession session = request.getSession();
            session.setAttribute("Username", username);

            if (resultSet.next()) {
                
                String userRole = resultSet.getString("role");
                if ("Admin".equals(userRole)) {
                    
                    response.sendRedirect("HomeAdmin.jsp");
                } else if ("Customer".equals(userRole)) {
                	
                    
                    response.sendRedirect("HomeCustomer.jsp");
                }
            } else {
                
                response.sendRedirect("login.jsp?error=1"); // You can handle errors in your login.jsp
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
