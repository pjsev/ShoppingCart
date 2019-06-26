package coding.exercise.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import coding.exercise.model.Customer;
import coding.exercise.model.CustomerRepository;
import coding.exercise.model.Item;
import coding.exercise.model.ItemRepository;
import coding.exercise.service.CustomerService;

@Controller
public class CustomerController {		
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ItemRepository itemRepository;
	
	// index page upon loading the application
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String greeting(ModelMap model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
	    return "add_customer";
    }
	
	// REST API: POST - add customer
	@PostMapping("/add")
	public Customer createCustomer(@RequestBody Customer customer) {		
		return customerService.saveCustomer(customer);		
	}
	
	// WEB Save customer during registration
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveCustomer(@Valid Customer customer, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			System.out.println("has errors");
			return "index";
		}
				
		Customer c = customerService.saveCustomer(customer);
		return "redirect:/viewproducts/" + c.getCustomerId();
	}
	
	// WEB List all available items and customer's basket in one page
	@RequestMapping(value="/viewproducts/{customerId}")
	public ModelAndView getAllProducts(@PathVariable long customerId) {
				
		Customer c = customerService.findCustomer(customerId);
		ModelAndView modelAndView = new ModelAndView("viewproducts");
		
		List<Item> list = itemRepository.findAll();
		modelAndView.addObject("customerId", customerId);
		modelAndView.addObject("list", list);
		modelAndView.addObject("myList", c.getItems());
		return modelAndView;
	}
	
	// WEB 
	@RequestMapping(value="/viewcustomers")
	public ModelAndView getAll() {		
		List<Customer> list = customerService.findAllCustomers();
		return new ModelAndView("viewcustomers", "list", list);
	}
	
	// REST API: GET - list of items in customer's basket
	@GetMapping("/items/{customerId}")
	public Set<Item> getItems(@PathVariable long customerId) {		
		Customer c = customerService.findCustomer(customerId);
		return c.getItems();
	}
	
	@GetMapping("/items/{customerId}/total") 
	public double getTotal(@PathVariable long customerId) {
		Set<Item> items = getItems(customerId);
		
		// logic should be placed inside service implementation
		double total = 0;
		for (Item i: items) {
			total += i.getPrice();
		}
		
		BigDecimal bd = new BigDecimal(total).setScale(2);
   
		return bd.doubleValue();
	}
	
	// WEB to display items for checkout
	@RequestMapping(value="/mybasket/{customerId}")
	public ModelAndView checkout(@PathVariable long customerId) {
		ModelAndView modelAndView = new ModelAndView("mybasket");
		Set<Item> items = getItems(customerId);
		
		// logic should be placed inside service implementation
		double total = 0;
		for (Item i: items) {
			total += i.getPrice();
		}
		
		NumberFormat formatter = new DecimalFormat("#0.00");     
		
		modelAndView.addObject("items", items);		
		modelAndView.addObject("total", formatter.format(total));
		return modelAndView;
	}
	
	// REST API: GET all items
	@GetMapping("/list")
	public List<Customer> getAllItems() {
		return customerService.findAllCustomers();
	}
	
}
