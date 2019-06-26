package coding.exercise.serviceimpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import coding.exercise.model.Customer;
import coding.exercise.model.CustomerRepository;
import coding.exercise.model.Item;
import coding.exercise.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer findCustomer(long id) {		
		return customerRepository.findById(id).orElse(null);
	}

	@Override
	public List<Customer> findAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public double computeCustomerItems(Set<Item> items) {
		
		double total = 0;
		for (Item i: items) {
			total += i.getPrice();
		}
		return total;
	}

}
