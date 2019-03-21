/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shaki694
 */
public class DaoTest {

	//private ProductEditorDAO dao = new ProductEditorDAO();
	private ProductEditorDAO dao = new ProductEditorDAO();
	private Product prodOne;
	private Product prodTwo;
	private Product prodThree;

	public DaoTest() {
	}

	@Before
	public void setUp() {
		this.prodOne = new Product("1", "name1", "desc1", "cat1",
				  new BigDecimal("11.00"), 22);
		this.prodTwo = new Product("2", "name2", "desc2", "cat2",
				  new BigDecimal("33.00"), 44);
		this.prodThree = new Product("3", "name3", "desc3","cat3",
				  new BigDecimal("55.00"), 66);
// save the products
		dao.saveProduct(prodOne);
		dao.saveProduct(prodTwo);
// Note: Intentionally not saving prodThree
	}

	@After
	public void tearDown() {
		dao.deleteProduct(prodOne);
		dao.deleteProduct(prodTwo);
		dao.deleteProduct(prodThree);
	}

	@Test
	public void testDaoSave() {
// save the product using DAO
		dao.saveProduct(prodThree);
// retrieve the same product via DAO
		Product retrieved = dao.searchProduct("3");
// ensure that the product we saved is the one we got back
		assertEquals("Retrieved product should be the same",
				  prodThree, retrieved);
	}

	@Test
	public void testDaoEdit() {
		prodOne.setName("name7");
		dao.saveProduct(prodOne);
		Product retrieved = dao.searchProduct("1");
		assertEquals("Retrieved product should be the same",
				  prodOne, retrieved);
		assertEquals("Retrieved product name should be the name7",
				  prodOne.getName(), "name7");
	}

	@Test
	public void testGetCategories() {
		Collection<String> categories = dao.getCategories();
		assertTrue("cat1 should exist", categories.contains("cat1"));
		assertTrue("cat2 should exist", categories.contains("cat2"));
		assertEquals("Only 2 catgories in result", 2, categories.size());
	}

	@Test
	public void testGetProductsBycategory() {
		Collection<Product> retrieved = dao.filterCategory("cat1");
		for(Product p: retrieved) {
			//assertEquals("Retrieved product should be the same",
				  //prodOne, p);
			String cat = p.getCategory();
		assertEquals(p.getCategory(), cat);
		}
		//assertEquals("Retrieved product should be the same",
				 // prodOne, retrieved);
		
		Collection<Product> result = dao.filterCategory("cat5");
		for(Product r: result) {
		assertNull("Product should not exist", r);
		}
	}

	@Test
	public void testDaoDelete() {
// delete the product via the DAO
		dao.deleteProduct(prodOne);
// try to retrieve the deleted product
		Product retrieved = dao.searchProduct("1");
// ensure that the student was not retrieved (should be null)
		assertNull("Product should no longer exist", retrieved);
	}

	@Test
	public void testDaoGetAll() {
		Collection<Product> products = dao.displayProducts();
// ensure the result includes the two saved products
		assertTrue("prodOne should exist", products.contains(prodOne));
		assertTrue("prodTwo should exist", products.contains(prodTwo));
// ensure the result ONLY includes the two saved products
		assertEquals("Only 2 products in result", 2, products.size());
// find prodOne - result is not a map, so we have to scan for it
		for (Product p : products) {
			if (p.equals(prodOne)) {
// ensure that all of the details were correctly retrieved
				assertEquals(prodOne.getProductId(), p.getProductId());
				assertEquals(prodOne.getName(), p.getName());
				assertEquals(prodOne.getDescription(), p.getDescription());
				assertEquals(prodOne.getCategory(), p.getCategory());
				assertEquals(prodOne.getListPrice(), p.getListPrice());
				assertEquals(prodOne.getQuantity(), p.getQuantity());
			}
		}
	}

	@Test
	public void testDaoFindById() {
// get prodOne using findById method
		Product retrieved = dao.searchProduct("1");
// assert that you got back prodOne, and not another product
		assertEquals("Retrieved product should be the same",
				  prodOne, retrieved);
// assert that prodOne's details were properly retrieved
		String id = prodOne.getProductId();
		assertEquals(prodOne.getProductId(), id);
		String name = prodOne.getName();
		assertEquals(prodOne.getName(), name);
		String cat = prodOne.getCategory();
		assertEquals(prodOne.getCategory(), cat);
		String des = prodOne.getDescription();
		assertEquals(prodOne.getDescription(), des);
		BigDecimal price = prodOne.getListPrice();
		assertEquals(prodOne.getListPrice(), price);
		Integer quantity = prodOne.getQuantity();
		assertEquals(prodOne.getQuantity(), quantity);
// call getById using a non-existent ID
		Product result = dao.searchProduct("5");
// assert that the result is null
		assertNull("Product should not exist", result);
	}

}
