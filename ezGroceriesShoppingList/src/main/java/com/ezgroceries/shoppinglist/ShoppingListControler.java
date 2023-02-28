package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.cocktail.CocktailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
public class ShoppingListControler {
private final ShoppingListService shoppingListService;

    public ShoppingListControler(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping(value="/shopping-lists")
    public ResponseEntity<Void> createShoppingList(@RequestBody ShoppingListDTO shoppingListDTO){
        UUID newShoppingListId = shoppingListService.createNewList(shoppingListDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{shoppingListId}")
                .buildAndExpand(newShoppingListId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value="/shopping-lists/{shoppingListId}/cocktails")
    public ResponseEntity<Void> addCocktailIngredientsToList(@PathVariable UUID shoppingListId, @RequestBody CocktailDTO cocktailDTO){

        shoppingListService.addIngredientsFromCocktail(shoppingListId,cocktailDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{shoppingListId}")
                .buildAndExpand(newShoppingListId).toUri();
        return ResponseEntity.created(location).build();
    }

}
