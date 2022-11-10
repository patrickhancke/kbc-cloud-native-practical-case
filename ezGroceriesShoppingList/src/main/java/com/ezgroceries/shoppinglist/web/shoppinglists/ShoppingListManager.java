package com.ezgroceries.shoppinglist.web.shoppinglists;

import com.ezgroceries.shoppinglist.web.cocktails.Cocktail;

import java.util.List;

public interface ShoppingListManager {

    List<ShoppingList> getAllShoppingLists();

    ShoppingList getShoppingList(String id);

    String createShoppingList(String name);

    void addCocktailToShoppingList(String listId, Cocktail cocktail);
}
