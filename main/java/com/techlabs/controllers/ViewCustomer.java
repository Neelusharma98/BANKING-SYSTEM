package com.techlabs.controllers;

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

@WebServlet("/ViewCustomer")
public class ViewCustomer extends HttpServlet {
    private static final long serialVersionUID = 1L;

  

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String searchValue = request.getParameter("searchValue");

            // Query to fetch customer details based on searchValue (customize as needed)
            String query = "SELECT * FROM CustomerDetail WHERE customerid = ? OR firstName LIKE ? OR lastName LIKE ?";
            List<Customer> customers = new ArrayList<>();

            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, searchValue);
                preparedStatement.setString(2, "%" + searchValue + "%");
                preparedStatement.setString(3, "%" + searchValue + "%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setCustomerid(resultSet.getInt("customerid"));
                        customer.setFirstName(resultSet.getString("firstName"));
                        customer.setLastName(resultSet.getString("lastName"));
                        customer.setEmail(resultSet.getString("email"));
                        customer.setPassword(resultSet.getString("password"));
                        customer.setMobile(resultSet.getString("mobile"));
                        customers.add(customer);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Set the customers attribute for JSP rendering
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("ViewCustomer.jsp").forward(request, response);
        }
    

}
