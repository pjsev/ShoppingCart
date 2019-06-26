package coding.exercise.service;

import java.util.List;
import java.util.Set;

import coding.exercise.model.Customer;
import coding.exercise.model.Item;

public interface CustomerService {
	
	Customer saveCustomer(Customer customer);
	Customer findCustomer(long id);
	List<Customer> findAllCustomers();
	
	double computeCustomerItems(Set<Item> items);
	
}
