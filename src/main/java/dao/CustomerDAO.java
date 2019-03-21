package dao;

import domain.Customer;

/**
 *
 * @author shaki694
 */
public interface CustomerDAO {

	void save(Customer customer);

	Customer getCustomer(String username);

	Boolean validateCredentials(String username, String password);
}
