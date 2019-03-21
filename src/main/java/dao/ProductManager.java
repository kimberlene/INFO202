/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author shaki694
 */
public interface ProductManager {

    void deleteProduct(Product product);

    Collection<Product> displayProducts();

    Collection<Product> filterCategory(String category);

    Collection<String> getCategories();

    void saveProduct(Product product);

    Product searchProduct(String productId);
    
}
