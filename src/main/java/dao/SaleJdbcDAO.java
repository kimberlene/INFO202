package dao;

import dao.DAOException;
import dao.SaleDAO;
import domain.Customer;
import domain.Product;
import domain.Sale;
import domain.SaleItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleJdbcDAO implements SaleDAO {

    private static final String url = "jdbc:h2:tcp://localhost:9098/project;IFEXISTS=TRUE";

    @Override
    public void save(Sale sale) {

         sale.setStatus("Awaiting Shipment");
        Connection con = JdbcConnection.getConnection(url);
        try {
            try (
                    PreparedStatement insertSaleStmt = con.prepareStatement(
                            "insert into Sale(Person_ID, Date_time, Status) values (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement insertSaleItemStmt = con.prepareStatement(
                            "insert into SaleItem(Product_ID, Sale_ID, Quantity, Price) values (?,?,?,?)");
                    PreparedStatement updateProductStmt = con.prepareStatement(
                            "update Product set Product_Quantity = ? where Product_ID = ?");) {

                // Since saving and sale involves multiple statements across
                // multiple tables we need to control the transaction ourselves
                // to ensure our DB remains consistent.
                //
                // Turn off auto-commit which effectively starts a new transaction.
                con.setAutoCommit(false);

                Customer customer = sale.getCustomer();
                // add a date to the sale if one doesn't already exist
                if (sale.getDate() == null) {
                    sale.setDate(new Date());
                }

                // convert sale date into to java.sql.Timestamp
                Date date = sale.getDate();
                Timestamp timestamp = new Timestamp(date.getTime());
                // #### save the sale ### //
                insertSaleStmt.setString(1, customer.getPersonId());
                insertSaleStmt.setTimestamp(2, timestamp);
                insertSaleStmt.setString(3, sale.getStatus());

                // ****
                // write code here that saves the timestamp and username in the
                // sale table using the insertSaleStmt statement.
                // ****
                insertSaleStmt.executeUpdate();
                
                // get the auto-generated sale ID from the database
                ResultSet rs = insertSaleStmt.getGeneratedKeys();

                String saleId = null;

                if (rs.next()) {
                    saleId = rs.getString(1);
                } else {
                    throw new DAOException("Problem getting generated Sale ID");
                }

                Collection<SaleItem> items = sale.getSaleItems();

                for (SaleItem item : items) {

                    Product product = item.getProduct();

                    // ****
                    // write code here that saves the sale item
                    // using the insertSaleItemStmt statement.
                    // ****
                    insertSaleItemStmt.setString(1, product.getProductId());
                    insertSaleItemStmt.setString(2, saleId);
                    insertSaleItemStmt.setInt(3, item.getQuantityPurchased());
                    insertSaleItemStmt.setBigDecimal(4, item.getSalePrice());
                    
                    insertSaleItemStmt.executeUpdate();
                    // ****
                    // write code here that updates the product quantity using
                    // the updateProductStmt statement.
                    // ****
                    Integer q = (product.getQuantity()) - (item.getQuantityPurchased());
                    updateProductStmt.setInt(1, q);
                    updateProductStmt.setString(2, product.getProductId());
                    updateProductStmt.executeUpdate();
                }

                // commit the transaction
                con.setAutoCommit(true);
            }
        } catch (SQLException ex) {

            Logger.getLogger(SaleJdbcDAO.class.getName()).log(Level.SEVERE, null, ex);

            try {
                // something went wrong so rollback
                con.rollback();

                // turn auto-commit back on
                con.setAutoCommit(true);

                // and throw an exception to tell the user something bad happened
                throw new DAOException(ex.getMessage(), ex);
            } catch (SQLException ex1) {
                throw new DAOException(ex1.getMessage(), ex1);
            }

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SaleJdbcDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
