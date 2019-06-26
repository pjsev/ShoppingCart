package coding.exercise.service;

import java.util.List;
import java.util.Set;

import coding.exercise.model.Customer;
import coding.exercise.model.Item;

public interface ItemService {
	
	Set<Item> getItems(Customer c);
	List<Item> listAllItems();
	
	Item saveInventoryItem(Item item);
}
