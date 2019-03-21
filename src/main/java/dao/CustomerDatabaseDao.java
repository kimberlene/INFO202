/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author shaki694
 */
public class CustomerDatabaseDao implements CustomerDAO {

    private static String url = "jdbc:h2:tcp://localhost:9098/project;IFEXISTS=TRUE";

    public CustomerDatabaseDao() {

    }

    public CustomerDatabaseDao(String url) {
        this.url = url;
    }

    @Override
    public void save(Customer customer) {
        String sql = "insert into Customer (Username, Firstname, Surname, Password, Email_Address, Address, Credit_Card_Details) values(?,?,?,?,?,?,?)";

        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            ResultSet rs = stmt.getGeneratedKeys();

            //stmt.setString(1, rs.toString());
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getFirstname());
            stmt.setString(3, customer.getSurname());
            stmt.setString(4, customer.getPassword());
            stmt.setString(5, customer.getEmailAddress());
            stmt.setString(6, customer.getAddress());
            stmt.setString(7, customer.getCreditCardDetails());

            stmt.executeUpdate();
            System.out.println("Saving customer: " + customer);

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Customer getCustomer(String username) {
        String sql = "select * from Customer where Username = ?";
        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("Person_Id");
                String user_name = rs.getString("Username");
                String firstname = rs.getString("Firstname");
                String surname = rs.getString("Surname");
                String password = rs.getString("Password");
                String email = rs.getString("Email_Address");
                String address = rs.getString("Address");
                String card = rs.getString("Credit_Card_Details");

                Customer c1 = new Customer(id, user_name, firstname, surname, password, email, address, card);
                return c1;
            }
            return null;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    public Boolean validateCredentials(String username, String password) {
        String sql = "select * from customer where Username = ? and Password = ?";
        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

}
