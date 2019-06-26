package coding.exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coding.exercise.model.Item;
import coding.exercise.model.ItemRepository;
import coding.exercise.service.ItemService;

@RestController
@RequestMapping("/products")
public class ItemController {

	@Autowired
	ItemService itemService;
	
	// REST API - add Item
	@PostMapping("/add")
	public Item createItem(@RequestBody Item item) {
		return itemService.saveInventoryItem(item);
	}
	
	// REST API - list all items
	@GetMapping("/list")
	public List<Item> getAllItems() {
		return itemService.listAllItems();
	}
}
