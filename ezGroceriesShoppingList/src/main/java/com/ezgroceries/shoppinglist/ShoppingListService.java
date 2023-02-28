package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.cocktail.CocktailDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShoppingListService {
    /*private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }*/

    public UUID createNewList(ShoppingListDTO shoppingListDTO) {
        /*ShoppingList shoppingList = ShoppingList.builder()
                .name(shoppingListDTO.getName())
                .build();
        ShoppingList newShoppingList = shoppingListRepository.save(shoppingList);
        return newShoppingList.getShoppingListId();
        */
        UUID shoppingListId= UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f");
         return shoppingListId;
    }

    public UUID addIngredientsFromCocktail(UUID shoppingListId, CocktailDTO cocktailDTO) {
        return shoppingListId;
    }
}
