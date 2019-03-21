/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.util.Objects;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNegative;
import net.sf.oval.constraint.NotNull;

/**
 *
 * @author shaki694
 */
public class Product implements Comparable<Product> {

    private String productId;
    @NotNull(message = "Name must be provided.")
    @NotBlank(message = "Name must be provided.")
    @Length(min = 2, message = "Name must contain at least two characters.")
    private String name;
    @NotNull(message = "Description must be provided.")
    @NotBlank(message = "Description must be provided.")
    private String description;
    @NotNull(message = "Category must be provided.")
    @NotBlank(message = "Category must be provided.")
    private String category;
    @NotNull(message = "Price must be provided.")
    @NotNegative(message = "Price must be zero or greater.")
    private BigDecimal listPrice;
    @NotNull(message = "Quantity must be provided.")
    @NotNegative(message = "Quantity must be zero or greater.")
    private Integer quantity;

    public Product() {
    }

    public Product(String productId, String name, String description, String category, BigDecimal listPrice, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.listPrice = listPrice;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product Id: " + productId + " Name: " + name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.productId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Product t) {
        String id = this.getProductId();
        String id2 = t.getProductId();
        return id.compareTo(id2);
    }

}
