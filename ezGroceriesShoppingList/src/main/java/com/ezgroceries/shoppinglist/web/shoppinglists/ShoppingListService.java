package com.ezgroceries.shoppinglist.web.shoppinglists;

import com.ezgroceries.shoppinglist.web.cocktails.Cocktail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShoppingListService {

    List<ShoppingList> getAllShoppingLists();

    Optional<ShoppingList> getShoppingList(UUID id);

    UUID createShoppingList(String name);

    void addCocktailToShoppingList(UUID listId, Cocktail cocktail);
}
