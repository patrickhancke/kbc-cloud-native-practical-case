package com.ezgroceries.shoppinglist.web.shoppinglists;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, UUID> {


}