package coding.exercise.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import coding.exercise.model.Customer;
import coding.exercise.model.CustomerRepository;
import coding.exercise.model.Item;
import coding.exercise.model.ItemRepository;

@Controller
public class ShoppingController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@PostMapping("/items")
	public Item addItem(@Valid @RequestBody Item item) {
		return itemRepository.save(item);
	}
	
	@GetMapping("/items")
	public List<Item> getItems() {
		return itemRepository.findAll();
	}
	
	@PostMapping("/addToCart/{customerId}/{itemId}")
	public Item addToCart(@PathVariable long customerId, @PathVariable long itemId) {
		Customer c = customerRepository.getOne(customerId);
		Item i = itemRepository.getOne(itemId);
		Set<Customer> customerItems = i.getCustomers();
		
		if (!customerItems.contains(c)) {
			customerItems.add(c);
			i.setCustomer(customerItems);
		}
		
		return itemRepository.save(i);
	}
	
	@RequestMapping(value="/addToBasket/{customerId}/{itemId}", method=RequestMethod.GET)
	public String addToBasket(@PathVariable long customerId, @PathVariable long itemId) {
		Customer c = customerRepository.getOne(customerId);
		Item i = itemRepository.getOne(itemId);
		Set<Customer> customerItems = i.getCustomers();
		
		if (!customerItems.contains(c)) {
			customerItems.add(c);
			i.setCustomer(customerItems);
		}
		
		itemRepository.save(i);
		return "redirect:/viewproducts/" + c.getCustomerId();
	}
	
	@PostMapping("/removeFromCart/{customerId}/{itemId}") 
	public Item removeFromCart(@PathVariable long customerId, @PathVariable long itemId) {
		Customer c = customerRepository.getOne(customerId);
		Item i = itemRepository.getOne(itemId);
		Set<Customer> customerItems = i.getCustomers();
		
		if (customerItems.contains(c)) {
			customerItems.remove(c);
			i.setCustomer(customerItems);
		}
		
		return itemRepository.save(i);
	}
	
	@RequestMapping(value="/removeFromBasket/{customerId}/{itemId}", method=RequestMethod.GET)
	public String removeFromBasket(@PathVariable long customerId, @PathVariable long itemId) {
		Customer c = customerRepository.getOne(customerId);
		Item i = itemRepository.getOne(itemId);
		Set<Customer> customerItems = i.getCustomers();
		
		if (customerItems.contains(c)) {
			customerItems.remove(c);
			i.setCustomer(customerItems);
		}
		
		itemRepository.save(i);
		return "redirect:/viewproducts/" + c.getCustomerId();
	}

}
