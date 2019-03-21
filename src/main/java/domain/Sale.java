/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import domain.SaleItem;
import net.sf.oval.constraint.DateRange;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 *
 * @author shaki694
 */
public class Sale {

    private List<SaleItem> saleItems = new ArrayList<>();
    private Customer customer;
    private String saleId;
    @NotNull(message = "Date must be provided.")
    @NotBlank(message = "Date must be provided.")
    @DateRange(format = "dd/mm/yy", max = "now", message = "Please provide a valid Date in the format 'dd/mm/yy'.")
    private Date date;
    @NotNull(message = "Status must be provided.")
    @NotBlank(message = "Status must be provided.")
    private String status;

    public Sale(Customer customer, String saleId, Date date, String status) {
        if (customer == null || saleId == null) {
            throw new IllegalArgumentException("A Sale must have a Customer");
        }
        this.customer = customer;
        this.saleId = saleId;
        this.date = date;
        this.status = status;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addItem(SaleItem item) {
        saleItems.add(item);
    }

    public BigDecimal getTotal() {
        BigDecimal sum = new BigDecimal(0);
        for (SaleItem i : saleItems) {
            sum = sum.add(i.getItemTotal());
        }
        return sum;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Sale{" + "saleItems=" + saleItems + ", customer=" + customer + ", saleId=" + saleId + ", date=" + date + ", status=" + status + '}';
    }



}
