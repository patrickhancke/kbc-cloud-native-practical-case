package com.ezgroceries.shoppinglist.entities;

import java.util.List;

public class ShoppingListResource {

    private String id;
    private String name;

    public ShoppingListResource(String shoppingListId, String name) {
        this.id = shoppingListId;
        this.name = name;
    }

    public String getShoppingListId() {
        return id;
    }

    public void setShoppingListId(String shoppingListId) {
        this.id = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
