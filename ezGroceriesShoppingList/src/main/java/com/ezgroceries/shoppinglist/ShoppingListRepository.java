package com.ezgroceries.shoppinglist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, UUID> {
}
