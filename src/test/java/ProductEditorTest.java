/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.DatabaseDao;
import domain.Product;
import gui.ProductEditor;
import gui.ProductReport;
import gui.helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author shaki694
 */
public class ProductEditorTest {

    private DatabaseDao dao;
    private DialogFixture fixture;
    private Robot robot;
    private Product prodOne;
    private Product prodTwo;

    public ProductEditorTest() {
    }

    @Before
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        robot.settings().delayBetweenEvents(75);

        Collection<String> categories = new TreeSet<>();
        categories.add("cat1");
        categories.add("cat2");
        
        Collection<Product> products = new ArrayList<>();
        prodOne = new Product("1", "name1", "desc1", "cat1", new BigDecimal(1), new Integer(1));
        prodTwo = new Product("2", "name2", "desc2", "cat2", new BigDecimal(2), new Integer(2));
        
        products.add(prodOne);
        products.add(prodOne);

        // create a mock for the DAO
        dao = mock(DatabaseDao.class);

        // stub the getMajors method to return the test majors
        when(dao.getCategories()).thenReturn(categories);
        when(dao.displayProducts()).thenReturn(products);
    }

    @After
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void testSave() {
        // create the dialog passing in the mocked DAO
        ProductEditor dialog = new ProductEditor(null, true, dao);

        // use AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();

        // enter some details into the UI components
        fixture.textBox("txtID").enterText("569");
        fixture.textBox("txtName").enterText("chips");
        fixture.textBox("txtDescription").enterText("food");
        fixture.comboBox("txtCategory").selectItem("cat1");
        fixture.textBox("txtPrice").enterText("15.01");
        fixture.textBox("txtQuantity").enterText("25");
        // click the save button
        fixture.button("btnSave").click();

        // create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        // verify that the DAO.save method was called, and capture the passed student
        verify(dao).saveProduct(argument.capture());

        // retrieve the passed student from the captor
        Product savedProduct = argument.getValue();

        // test that the student's details were properly saved
        assertEquals("Ensure the ID was saved", "569", savedProduct.getProductId());
        assertEquals("Ensure the name was saved", "chips", savedProduct.getName());
        assertEquals("Ensure the description was saved", "food", savedProduct.getDescription());
        assertEquals("Ensure the category was saved", "cat1", savedProduct.getCategory());
        assertEquals("Ensure the price was saved", new BigDecimal("15.01"), savedProduct.getListPrice());
        assertEquals("Ensure the quantity was saved", new Integer("25"), savedProduct.getQuantity());
    }

    @Test
    public void testEdit() {
        Product p = new Product("1234", "p1", "d1", "cat1", new BigDecimal(14.09), 32);
        ProductEditor dialog = new ProductEditor(null, true, p, dao);

        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();

        fixture.textBox("txtID").requireText("1234");
        fixture.textBox("txtName").requireText("p1");
        fixture.textBox("txtDescription").requireText("d1");
        fixture.comboBox("txtCategory").requireSelection("cat1");
        fixture.textBox("txtPrice").requireText("14.09");
        fixture.textBox("txtQuantity").requireText("32");

        fixture.textBox("txtName").selectAll().deleteText().enterText("Product1");
        fixture.comboBox("txtCategory").selectItem("cat2");

        fixture.button("btnSave").click();
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
        verify(dao).saveProduct(argument.capture());

        Product editedProduct = argument.getValue();
        assertEquals("Ensure the name was changed", "Product1", editedProduct.getName());
        assertEquals("Ensure the category was changed", "cat2", editedProduct.getCategory());

    }

    @Test
    public void testViewAllProducts() {
        // create dialog
        ProductReport dialog = new ProductReport(null, true, dao);

        // use AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);

        fixture.show().requireVisible();

        verify(dao).displayProducts();

        // get the model 
        SimpleListModel model = (SimpleListModel) fixture.list("productsJlist").target().getModel();

        // check the contents
        assertTrue("list contains the expected product", model.contains(prodOne));
        assertEquals("list contains the correct number of products", 2, model.getSize());

        // select item to edit 
        fixture.list("productsJlist").selectItem(prodOne.toString());

        // click the edit button
        fixture.button("Edit").click();

        // find the data entry dialog that appears 
        DialogFixture editDialog = fixture.dialog("productDialog");
        
        editDialog.requireVisible();

        // check that the data entry dialog is displaying selected item
        editDialog.textBox("txtID").requireText(prodOne.getProductId().toString());
    }
}
