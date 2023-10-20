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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CustomerViewController")
public class CustomerViewController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the "viewCustomers" parameter is present and set to "true"
        String viewCustomers = request.getParameter("viewCustomers");
        if (viewCustomers != null && viewCustomers.equals("true")) {
            List<Customer> customers = new ArrayList<>();

            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM CustomerDetail");
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Customer customer = new Customer();
                    //customer.setCustomerid(resultSet.getInt("customerid"));
                    customer.setFirstName(resultSet.getString("firstName"));
                    customer.setLastName(resultSet.getString("lastName"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setPassword(resultSet.getString("password"));
                    customer.setMobile(resultSet.getString("mobile"));
                    customers.add(customer);
                }

                // Set the list of customers as an attribute in the request
                request.setAttribute("customers", customers);

                // Forward the request to the JSP page (customerList.jsp)
                request.getRequestDispatcher("CustomerList.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Handle other cases or provide a message
            response.getWriter().println("Invalid request.");
        }
    }
}
