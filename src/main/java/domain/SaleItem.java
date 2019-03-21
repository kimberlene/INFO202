/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 *
 * @author shaki694
 */
public class SaleItem {
    private Product product;
    private Sale sale;
    @NotNull(message = "Quantity purchased must be provided.")
    @NotBlank(message = "Quantity purchased must be provided.")
    private Integer quantityPurchased;
    @NotNull(message = "Price must be provided.")
    @NotBlank(message = "Price must be provided.")
    private BigDecimal salePrice;

    public SaleItem(Product product, Sale sale, Integer quantityPurchased, BigDecimal salePrice) {
        if(product == null) {
            throw new IllegalArgumentException("A SaleItem must have a Product");
        }
        if(sale == null) {
            throw new IllegalArgumentException("A SaleItem must have a Sale ID");
        }
        
        this.product = product;
        this.quantityPurchased = quantityPurchased;
        this.salePrice = salePrice;
    }

    public Integer getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(Integer quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
	 
	 public BigDecimal getItemTotal() {
		 BigDecimal result = salePrice.multiply(new BigDecimal(quantityPurchased));
		 return result;
	 }

    @Override
    public String toString() {
        return "Product: " + product + " Quantity Purchased: " + quantityPurchased +
                " Sale Price: " + salePrice + " Total: " + getItemTotal() +"\n";
    }



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
}
