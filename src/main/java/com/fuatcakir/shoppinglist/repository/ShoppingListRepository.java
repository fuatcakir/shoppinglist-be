package com.fuatcakir.shoppinglist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fuatcakir.shoppinglist.beans.ShoppingList;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long>{

	List<ShoppingList> findByNameContainingOrDescriptionContaining(String name, String desc);
	
	List<ShoppingList> findByCreatedBy(String owner);

}
