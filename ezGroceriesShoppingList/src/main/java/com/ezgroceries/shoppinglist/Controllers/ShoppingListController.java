package com.ezgroceries.shoppinglist.Controllers;

import com.ezgroceries.shoppinglist.Classes.ShoppingList;
import com.ezgroceries.shoppinglist.Managers.ShoppingListManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ShoppingListController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ShoppingListManager shoppingListManager;

    @Autowired
    public ShoppingListController(ShoppingListManager shoppingListManager) {this.shoppingListManager = shoppingListManager;
    }

    @PostMapping(value="/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public void createShoppingList(@RequestBody String newShoppingList){
        logger.info("create shopping list: " + newShoppingList);
    }

    @PostMapping(value="/shopping-lists/{shoppingListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCocktail(@PathVariable String shoppingListId, @RequestBody String CocktailId){
        logger.info("add cocktail to shopping list.  ShoppingListId = " + shoppingListId + ". CockailId = " + CocktailId);
    }

   @GetMapping(value="/shopping-lists")
    public List<ShoppingList> shoppingSummary(){
        logger.info("get all shopping lists");
        return shoppingListManager.getAllShoppingLists();
    }


    @GetMapping(value="/shopping-lists/{shoppingListId}")
    public List<ShoppingList> shoppingSummary(@PathVariable String shoppingListId){
        logger.info(shoppingListId);
        return shoppingListManager.getShoppingList(shoppingListId);
    }
}
