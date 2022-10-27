package com.ezgroceries.shoppinglist.web.shoppinglists;

public class ShoppingList {

    String shoppingListId;
    String name;
    String[] ingredients; //TODO maybe change this later

    public ShoppingList(String shoppingListId, String name, String[] ingredients) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(String shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }
}
