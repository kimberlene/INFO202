
import dao.DatabaseDao;
import gui.ProductAdministration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shaki694
 */
public class Administrator {

    private static DatabaseDao database = new DatabaseDao();

    public static void main(String[] args) {

        // create the frame instance
        ProductAdministration newAdmin = new ProductAdministration(database);

        // centre the frame on the screen
        newAdmin.setLocationRelativeTo(null);

        // show the frame
        newAdmin.setVisible(true);

    }
}
