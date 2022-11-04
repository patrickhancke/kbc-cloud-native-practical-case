package com.ezgroceries.shoppinglist.Managers;

import com.ezgroceries.shoppinglist.Classes.Cocktail;
import com.ezgroceries.shoppinglist.Classes.ShoppingList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ShoppingListManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<ShoppingList> getAllShoppingLists(){

        List<ShoppingList> result = new ArrayList<>();
        result.add(new ShoppingList("4ba92a46-1d1b-4e52-8e38-13cd56c7224c", "Stephanie's birthday",
                Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt","Blue Curacao")));
        result.add(new ShoppingList("6c7d09c2-8a25-4d54-a979-25ae779d2465", "My Birthday",
                Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt","Blue Curacao")));
        return result;
    }

    public List<ShoppingList> getShoppingList(String ShoppingList) {

        logger.info("GetShoppingList");
        logger.info(ShoppingList);

        List<ShoppingList> result = new ArrayList<>();

        if (ShoppingList.equals("90689338-499a-4c49-af90-f1e73068ad4f")) {

            result.add(new ShoppingList("290689338-499a-4c49-af90-f1e73068ad4f", "Stephanie's birthday",
                    Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt","Blue Curacao")));
        }
        return result;
    }
}