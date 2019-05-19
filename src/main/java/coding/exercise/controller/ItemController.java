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

@RestController
@RequestMapping("/products")
public class ItemController {

	@Autowired
	ItemRepository itemRepository;
	
	// REST API - add Item
	@PostMapping("/add")
	public Item createItem(@RequestBody Item item) {
		return itemRepository.save(item);
	}
	
	// REST API - list all items
	@GetMapping("/list")
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}
}
