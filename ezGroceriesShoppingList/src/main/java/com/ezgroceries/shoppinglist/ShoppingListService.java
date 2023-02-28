package com.ezgroceries.shoppinglist;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public UUID createNewList(ShoppingListDTO shoppingListDTO) {
        ShoppingList shoppingList = ShoppingList.builder()
                .name(shoppingListDTO.getName())
                .build();
        ShoppingList newShoppingList = shoppingListRepository.save(shoppingList);
        return newShoppingList.getShoppingListId();
    }
}
