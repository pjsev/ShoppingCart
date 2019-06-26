package coding.exercise.serviceimpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coding.exercise.model.Customer;
import coding.exercise.model.CustomerRepository;
import coding.exercise.model.Item;
import coding.exercise.model.ItemRepository;
import coding.exercise.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ItemRepository itemRepository;

	@Override
	public Set<Item> getItems(Customer c) {
		return c.getItems();
	}

	@Override
	public List<Item> listAllItems() {
		return itemRepository.findAll();
	}

}
