package com.ezgroceries.shoppinglist.managers;

import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShoppingListManager_old {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<ShoppingListEntity> getAllShoppingLists(){

       List<ShoppingListEntity> result = new ArrayList<>();
    /*    result.add(new ShoppingListEntity("4ba92a46-1d1b-4e52-8e38-13cd56c7224c", "Stephanie's birthday"));
             //   Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt","Blue Curacao")));
        result.add(new ShoppingListEntity("6c7d09c2-8a25-4d54-a979-25ae779d2465", "My Birthday"));
              //  Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt","Blue Curacao")));*/
        return result;
    }

    public List<ShoppingListEntity> getShoppingList(String ShoppingList) {

       logger.info("GetShoppingList");
        logger.info(ShoppingList);

        List<ShoppingListEntity> result = new ArrayList<>();

      /*  if (ShoppingList.equals("290689338-499a-4c49-af90-f1e73068ad4f")) {

            result.add(new ShoppingListEntity("290689338-499a-4c49-af90-f1e73068ad4f", "Stephanie's birthday"));
               //     Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt","Blue Curacao")));
        }*/
        return result;
    }
}