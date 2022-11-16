package com.ezgroceries.shopinglist.shoppinglist;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.Size;

public class ShoppingList {

    private UUID shoppingListId;
    @Valid
    @Size(max = 100, message = "maximum param length reached")
    private String name;
    private Set<String> ingredients;
    private List<Cocktail> cocktails = new ArrayList<>();


    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Cocktail> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<Cocktail> cocktails) {
        this.cocktails = cocktails;
    }
}
