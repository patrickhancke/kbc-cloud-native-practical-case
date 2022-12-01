package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.entities.CocktailResource;
import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
public class ShoppingListController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ShoppingListService shoppingListService;

   // @Autowired
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping(value="/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public String createShoppingList(@RequestParam(required = false) String name){
        logger.info("create shopping list: " + name);
        return shoppingListService.createShoppingList(name);
    }

    @PostMapping(value="/shopping-lists/{shoppingListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCocktail(@PathVariable UUID shoppingListId, @RequestParam(required = false) UUID cocktailId){
        logger.info("add cocktail to shopping list.  ShoppingListId = " + shoppingListId + ". CockailId = " + cocktailId);
        shoppingListService.addCocktailToList(shoppingListId,cocktailId);
    }

   @GetMapping(value="/shopping-lists")
    public List<CocktailResource> shoppingSummary(){
        logger.info("get all shopping lists");
        return shoppingListService.getAllShoppingLists();

    }


   @GetMapping(value="/shopping-lists/{shoppingListId}")
    public List<CocktailResource> shoppingSummary(@PathVariable UUID shoppingListId){
        logger.info(String.valueOf(shoppingListId));
       return shoppingListService.getShoppingList(shoppingListId);
    }
}
