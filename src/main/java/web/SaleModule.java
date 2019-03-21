/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.SaleDAO;
import domain.Customer;
import domain.Sale;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author shaki694
 */
public class SaleModule extends Jooby {

    public SaleModule(SaleDAO dao) {

        post("/api/sales", (req, rsp) -> {
            Sale sale = req.body().to(Sale.class);
            System.out.println(sale);
            dao.save(sale);
            CompletableFuture.runAsync(() -> {
                // code to send email goes here
                try {
                    Email email = new SimpleEmail();
                    email.setHostName("localhost");
                    email.setSmtpPort(2525);
                    email.setFrom("theCandyShop@gmail.com");

                    email.setSubject("Confirmation of your Order");
                    email.setMsg("Thank you for your order. The following items will be "
                            + "shipped to your address soon.\n" + sale.getSaleItems());
                    
                    Customer c = sale.getCustomer();
                    email.addTo(c.getEmailAddress());

                    email.send();
                } catch (EmailException ex) {
                    Logger.getLogger(SaleModule.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
            rsp.status(Status.CREATED);
        });

    }
}
