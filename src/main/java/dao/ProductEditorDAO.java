/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author shaki694
 */
public class ProductEditorDAO implements ProductManager{

    private static Collection<Product> productList = new HashSet<>();
    private static Collection<String> categoryList = new HashSet<>();
    private static Map<String, Product> productMap = new HashMap<>();
    private static Multimap<String,Product> categoryMultimap = HashMultimap.create();

    @Override
    public void saveProduct(Product product) {
        productList.add(product);
        productMap.put(product.getProductId(), product);
        String category = product.getCategory();
        //if (categoryList.contains(category) == false) {
            categoryList.add(category);
        //}
        categoryMultimap.put(category, product);

    }

    @Override
    public Collection<Product> displayProducts() {
        return productList;
    }

    @Override
    public Collection<String> getCategories() {
        return categoryList;
    }

    @Override
    public void deleteProduct(Product product) {
        productList.remove(product);
        productMap.remove(product.getProductId(), product);
        categoryMultimap.remove(product.getCategory(), product);
    }

    @Override
    public Product searchProduct(String productId) {
        Product product = productMap.get(productId);
        return product;
    }
    
    @Override
    public Collection<Product> filterCategory(String category) {
        Collection<Product> pro = categoryMultimap.get(category);
        return pro;
    }
}
