/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author shaki694
 */
public class DatabaseDao implements ProductManager {

    private static String url = "jdbc:h2:tcp://localhost:9098/project;IFEXISTS=TRUE";

    public DatabaseDao() {

    }

    public DatabaseDao(String url) {
        this.url = url;
    }

    @Override
    public void deleteProduct(Product product) {
        String sql ="delete from Product where Product_ID =?";
        
         try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, product.getProductId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<Product> displayProducts() {
        String sql = "select * from Product order by Product_Id";

        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();

            Collection<Product> products = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString("Product_ID");
                String name = rs.getString("Product_Name");
                String description = rs.getString("Product_Description");
                String category = rs.getString("Product_Category");
                BigDecimal price = rs.getBigDecimal("Product_Price");
                Integer quantity = rs.getInt("Product_Quantity");

                Product p1 = new Product(id, name, description, category, price, quantity);
                products.add(p1);

            }
            return products;
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<Product> filterCategory(String category) {
        String sql = "select * from Product where Product_Category=? order by Product_Id";
        
         try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
             stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();

            Collection<Product> products = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString("Product_ID");
                String name = rs.getString("Product_Name");
                String description = rs.getString("Product_Description");
                String cat = rs.getString("Product_Category");
                BigDecimal price = rs.getBigDecimal("Product_Price");
                Integer quantity = rs.getInt("Product_Quantity");
                
                Product p1 = new Product(id, name, description, cat, price, quantity);
                products.add(p1);

            }
            return products;
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<String> getCategories() {
        String sql = "select distinct PRODUCT_CATEGORY from Product";

        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();

            Collection<String> categories = new HashSet<>();
            while (rs.next()) {
                String cat = rs.getString("Product_Category");
                categories.add(cat);
            }
            return categories;
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    public void saveProduct(Product product) {
        String sql = "merge into Product (Product_ID, Product_Name, Product_Description, Product_Category, Product_Price, Product_Quantity) values(?,?,?,?,?,?)";

        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, product.getProductId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getCategory());
            stmt.setBigDecimal(5, product.getListPrice());
            stmt.setInt(6, product.getQuantity());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Product searchProduct(String productId) {
        String sql = "select * from product where Product_ID = ?";
        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("Product_Id");
                String name = rs.getString("Product_Name");
                String description = rs.getString("Product_Description");
                String category = rs.getString("Product_Category");
                BigDecimal price = rs.getBigDecimal("Product_Price");
                Integer quantity = rs.getInt("Product_Quantity");

                Product p1 = new Product(id, name, description, category, price, quantity);
                return p1;
            } 
                return null;
            

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }
}
