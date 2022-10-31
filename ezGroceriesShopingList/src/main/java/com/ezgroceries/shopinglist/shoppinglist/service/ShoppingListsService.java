package com.ezgroceries.shopinglist.shoppinglist.service;

import com.ezgroceries.shopinglist.NotFoundException;
import com.ezgroceries.shopinglist.shoppinglist.ShoppingList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Controller;

@Controller
public class ShoppingListsService {

    private static final Map<UUID, ShoppingList> shoppingLists = new HashMap<>();

    public ShoppingList getShoppingList(UUID id) {
        ShoppingList shoppingList = shoppingLists.get(id);
        if (shoppingList == null) {
            throw new NotFoundException("shopping list with id: " + id + " not found");
        }

        return shoppingList;
    }

    public ShoppingList createShoppingList(String name) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setShoppingListId(UUID.randomUUID());
        shoppingList.setName(name);
        shoppingLists.put(shoppingList.getShoppingListId(), shoppingList);

        return shoppingList;
    }
}
