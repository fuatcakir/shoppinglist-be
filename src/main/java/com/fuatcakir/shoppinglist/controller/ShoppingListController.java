package com.fuatcakir.shoppinglist.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fuatcakir.shoppinglist.beans.ShoppingList;
import com.fuatcakir.shoppinglist.repository.ShoppingListRepository;

@RestController
@RequestMapping("/api")
public class ShoppingListController {

	@Autowired
	ShoppingListRepository shoppingListRepository;

 
	// List
	@GetMapping(value = "/findByNameorDesc/")
	public List<ShoppingList> getMethodName(@RequestParam String name, @RequestParam String desc) {
		return shoppingListRepository.findByNameContainingOrDescriptionContaining(name, desc);
	}

	// Search by creator
	@GetMapping(value = "/findByCreator/{owner}")
	public List<ShoppingList> findByCreator(@PathVariable String owner) {
		return shoppingListRepository.findByCreatedBy(owner);
	}

	// Search
	@GetMapping(value = "/findById/{id}")
	public ShoppingList getMethodName(@PathVariable Long id) {
		return shoppingListRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	// Create
	@PostMapping(value = "/add/")
	public ShoppingList add(@RequestBody ShoppingList entity) {
		if (entity.getCreateDate() == null) {
			entity.setCreateDate(Calendar.getInstance().getTime());
		}
		if (entity.getUpdateDate() == null) {
			entity.setUpdateDate(Calendar.getInstance().getTime());
		}
		ShoppingList saved = shoppingListRepository.save(entity);
		return saved;
	}

	// Update
	@PutMapping(value = "update/{id}")
	public ShoppingList update(@PathVariable Long id, @RequestBody ShoppingList entity) {
		ShoppingList current = shoppingListRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		if (entity.getCount() != null) {
			current.setCount(entity.getCount());
		}
		if (StringUtils.hasText(entity.getDescription())) {
			current.setDescription(entity.getDescription());
		}
		if (StringUtils.hasText(entity.getName())) {
			current.setName(entity.getName());
		}
		if (StringUtils.hasText(entity.getCreatedBy())) {
			current.setCreatedBy(entity.getCreatedBy());
		}
		
		current.setUpdateDate(Calendar.getInstance().getTime());
		

		ShoppingList saved = shoppingListRepository.save(current);
		return saved;
	}

	// Delete
	@DeleteMapping(value = "delete/{id}")
	public String deleteMethodName(@PathVariable Long id) {
		shoppingListRepository.deleteById(id);
		return "Success." + id + " deleted.";
	}

}
