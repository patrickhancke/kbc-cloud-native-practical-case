package com.ezgroceries.shoppinglist.web.shoppinglists;

import java.util.List;

public interface ShoppingListManager {

    List<ShoppingList> getAllShoppingLists();

    ShoppingList getShoppingList(String id);

    void createShoppingList(String name);

    void addCocktailToShoppingList(String listId, String cocktailId);
}
