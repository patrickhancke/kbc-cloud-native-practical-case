package com.ezgroceries.shopinglist.shoppinglist.persistence;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingListRepository extends CrudRepository<ShoppingListEntity, UUID> {
}
