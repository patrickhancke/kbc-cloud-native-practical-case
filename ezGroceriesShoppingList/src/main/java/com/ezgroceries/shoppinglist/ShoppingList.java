package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.cocktail.Ingredient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ShoppingList {
    private UUID shoppingListId;
    private String name;
    private List<Ingredient> ingredientList;
}
