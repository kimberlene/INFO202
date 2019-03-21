package web;

import domain.Customer;
import dao.CustomerDAO;
import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author shaki694
 */
public class CustomerModule extends Jooby {

	public CustomerModule(CustomerDAO dao) {
		get("/api/customers/:username", (req) -> {
			String username = req.param("username").value();
                        if(dao.getCustomer(username) == null) {
                           throw new Err(Status.NOT_FOUND);
                        }
			return dao.getCustomer(username);
                        
				  });
		post("/api/register", (req, rsp) -> {
			Customer customer = req.body().to(Customer.class);
			dao.save(customer);
			rsp.status(Status.CREATED);
		});
	}
}
