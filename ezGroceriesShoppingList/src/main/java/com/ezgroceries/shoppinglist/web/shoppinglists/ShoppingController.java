package com.ezgroceries.shoppinglist.web.shoppinglists;

import com.ezgroceries.shoppinglist.web.cocktails.Cocktail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class ShoppingController {
    private static final Logger log = LoggerFactory.getLogger(ShoppingController.class);
    private final ShoppingListManager shoppingListManager;

    public ShoppingController(ShoppingListManager shoppingListManager){
        this.shoppingListManager = shoppingListManager;
    }

    @GetMapping(value="/shopping-lists/{shoppingListId}")
    public ResponseEntity<ShoppingList> getShoppingList(@PathVariable(name="shoppingListId") String listId){
        log.info("Getting shopping list {}", listId);
        ShoppingList found = shoppingListManager.getShoppingList(listId);
        return ResponseEntity.ok().body(found);
    } //TODO now always returning OK, also set 'not found' logic

    @GetMapping(value="/shopping-lists")
    public ResponseEntity<List<ShoppingList>> getAllShoppingLists(){
        log.info("Getting all shopping lists");
        List<ShoppingList> results = shoppingListManager.getAllShoppingLists();
        return ResponseEntity.ok().body(results);
    }

    @PostMapping(value = "/shopping-lists", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createShoppingList(@RequestBody ShoppingList shoppingList) {

        String name = shoppingList.getName();
        log.info("create shopping list for {}", name);
        String listId = shoppingListManager.createShoppingList(name);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{resourceId}")
                .buildAndExpand(listId)
                .toUri();
        return ResponseEntity.created(location).build();
    }



    @PostMapping(value = "/shopping-lists/{shoppingListId}/cocktails")
    public ResponseEntity<Void> addCocktailToList(@PathVariable(name="shoppingListId") String listId, @RequestBody Cocktail cocktail) {

        log.info("Add cocktail {} to shopping list {}", cocktail.getCocktailId(), listId);
        shoppingListManager.addCocktailToShoppingList(listId, cocktail);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{resourceId}")
                .buildAndExpand(cocktail.getCocktailId())
                .toUri(); //TODO location of the resource = location of the cocktail?
        return ResponseEntity.created(location).build();
    }
}