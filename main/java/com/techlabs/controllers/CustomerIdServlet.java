package com.techlabs.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//CustomerIdServlet.java
import com.techlabs.controllers.DbConnection;

import java.io.IOException;
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

@WebServlet("/CustomerIdServlet")
public class CustomerIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     List<Integer> customerIds = new ArrayList<>();

     try {
         Connection connection = DbConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement("SELECT customerid FROM CustomerDetail");
         ResultSet resultSet = statement.executeQuery();

         while (resultSet.next()) {
             int customerId = resultSet.getInt("customerid");
             customerIds.add(customerId);
         }

         resultSet.close();
         statement.close();
         connection.close();
     } catch (SQLException e) {
         e.printStackTrace();
     }

     // Store the customer IDs in a request attribute
     request.setAttribute("customerIds", customerIds);

     // Forward the request to the JSP page
     request.getRequestDispatcher("AddBankAccount.jsp").forward(request, response);
 }
}
