package com.ezgroceries.shoppinglist.web.shoppinglists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class ShoppingController {
    private static final Logger log = LoggerFactory.getLogger(ShoppingController.class);
    private final ShoppingListManager shoppingListManager;

    public ShoppingController(ShoppingListManager shoppingListManager){
        this.shoppingListManager = shoppingListManager;
    }

    @GetMapping(value="/shopping-lists/{shoppingListId}")
    public ResponseEntity<ShoppingList> getShoppingList(@PathVariable(name="shoppingListId") String listId){
        log.info("Getting shopping list " + listId);
        ShoppingList found = shoppingListManager.getShoppingList(listId);
        return ResponseEntity.ok().body(found);
    }

    @GetMapping(value="/shopping-lists")
    public ResponseEntity<List<ShoppingList>> getAllShoppingLists(){
        log.info("Getting all shopping lists");
        List<ShoppingList> results = shoppingListManager.getAllShoppingLists();
        return ResponseEntity.ok().body(results);
    }

    @PostMapping(value = "/shopping-lists")
    public ResponseEntity<Void> createShoppingList(@RequestParam String name) { // (name="name") default
        log.info("create shopping list for " + name);
        shoppingListManager.createShoppingList(name);
        URI location = null; //TODO to determine location
        return ResponseEntity.created(location).build();
    }


    @PostMapping(value = "/shopping-lists/{shoppingListId}/cocktails")
    public ResponseEntity<Void> addCocktailToList(@PathVariable(name="shoppingListId") String listId, @RequestParam String cocktailId) {
        log.info("Add cocktail " + cocktailId + " to shopping list " + listId);
        shoppingListManager.addCocktailToShoppingList(listId, cocktailId);
        URI location = null; //TODO to determine location
        return ResponseEntity.created(location).build();
    }
}
