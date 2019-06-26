package coding.exercise.service;

import java.util.List;

import coding.exercise.model.Customer;

public interface CustomerService {
	
	Customer saveCustomer(Customer customer);
	Customer findCustomer(long id);
	List<Customer> findAllCustomers();
	
}
