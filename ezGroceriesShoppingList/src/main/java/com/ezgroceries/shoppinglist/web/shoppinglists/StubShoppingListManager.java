package com.ezgroceries.shoppinglist.web.shoppinglists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class StubShoppingListManager implements ShoppingListManager{
    private static final Logger log = LoggerFactory.getLogger(StubShoppingListManager.class);

    private final ShoppingList stephanieDummyShoppingList = new ShoppingList("90689338-499a-4c49-af90-f1e73068ad4f",
            "Stephanie's birthday",
            new String[]{"Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"});

    private final ShoppingList myDummyShoppingList = new ShoppingList("6c7d09c2-8a25-4d54-a979-25ae779d2465",
            "My birthday",
            new String[]{"Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"});


    @Override
    public List<ShoppingList> getAllShoppingLists() {
        List<ShoppingList> result = new ArrayList<>();
        result.add(stephanieDummyShoppingList);
        result.add(myDummyShoppingList);
        return result;
    }

    @Override
    public ShoppingList getShoppingList(String id) {
        log.info("returning default stephanie list instead of " + id);
        return stephanieDummyShoppingList;
    }

    @Override
    public void createShoppingList(String name) {
        log.info("doing nothing really..");
    }

    @Override
    public void addCocktailToShoppingList(String listId, String cocktailId) {
        log.info("doing nothing really..");
    }
}
