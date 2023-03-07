package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.cocktail.CocktailDTO;
import com.ezgroceries.shoppinglist.cocktail.Ingredient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListService {


    public UUID createNewList(ShoppingListNameDTO shoppingListNameDTO) {

        UUID shoppingListId= UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f");
         return shoppingListId;
    }

    public UUID addIngredientsFromCocktail(UUID shoppingListId, CocktailDTO cocktailDTO) {
        return shoppingListId;
    }

    public ShoppingListDTO getShoppingList(UUID shoppingListId) {
        return ShoppingListDTO.builder()
                .shoppingListId(shoppingListId)
                .name("Stephanie's birthday")
                .ingredients(Arrays.asList(Ingredient.builder().name("Tequila").build(),
                        Ingredient.builder().name("Triple sec").build(),
                        Ingredient.builder().name("Lime juice").build(),
                        Ingredient.builder().name("Salt").build(),
                        Ingredient.builder().name("Blue Curacao").build()))
                .build();
    }

    public List<ShoppingListDTO> getAllShoppingList() {
        List<ShoppingListDTO> shoppingListDTOS =  new ArrayList<>();
        shoppingListDTOS.add(ShoppingListDTO.builder()
                .shoppingListId(UUID.fromString("4ba92a46-1d1b-4e52-8e38-13cd56c7224c"))
                .name("Stephanie's birthday")
                .ingredients(Arrays.asList(Ingredient.builder().name("Tequila").build(),
                        Ingredient.builder().name("Triple sec").build(),
                        Ingredient.builder().name("Lime juice").build(),
                        Ingredient.builder().name("Salt").build(),
                        Ingredient.builder().name("Blue Curacao").build()))
                .build());
        shoppingListDTOS.add(ShoppingListDTO.builder()
                .shoppingListId(UUID.fromString("6c7d09c2-8a25-4d54-a979-25ae779d2465"))
                .name("My Birthday")
                .ingredients(Arrays.asList(Ingredient.builder().name("Tequila").build(),
                        Ingredient.builder().name("Triple sec").build(),
                        Ingredient.builder().name("Lime juice").build(),
                        Ingredient.builder().name("Salt").build(),
                        Ingredient.builder().name("Blue Curacao").build()))
                .build());
        return shoppingListDTOS;
    }
}
