package com.ezgroceries.shopinglist.shoppinglist.web;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.service.CocktailsService;
import com.ezgroceries.shopinglist.NotFoundException;
import com.ezgroceries.shopinglist.shoppinglist.ShoppingList;
import com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListsService;
import java.net.URI;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ShoppingListController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListController.class);

    private final ShoppingListsService shoppingListsService;
    private final CocktailsService cocktailsService;

    public ShoppingListController(ShoppingListsService shoppingListsService,
            CocktailsService cocktailsService) {
        this.shoppingListsService = shoppingListsService;
        this.cocktailsService = cocktailsService;
    }

    @GetMapping("/shopping-lists/{shoppingListId}")
    @ResponseBody
    public ShoppingList getShoppingList(@PathVariable(name = "shoppingListId") UUID shoppingListId) {
        ShoppingList shoppingList = shoppingListsService.getShoppingList(shoppingListId);
        shoppingList.setIngredients(shoppingList.getCocktails().stream()
                .flatMap(cocktail -> cocktail.getIngredients().stream()).collect(Collectors.toSet()));

        return shoppingList;
    }

    @PostMapping("/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody String name) {
        ShoppingList shoppingList = shoppingListsService.createShoppingList(name);
        return entityWithLocation(shoppingList.getShoppingListId());
    }

    @PostMapping("/shopping-lists/{shoppingListId}/cocktails")
    public ResponseEntity<Void> addCocktail(@PathVariable UUID shoppingListId, @RequestParam UUID cocktailId) {
        ShoppingList shoppingList = shoppingListsService.getShoppingList(shoppingListId);
        Cocktail cocktail = cocktailsService.getCocktail(cocktailId);
        shoppingList.getCocktails().add(cocktail);
        return entityWithLocation(cocktailId);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ NotFoundException.class })
    public void handleDataIntegrity(Exception ex) {
        LOGGER.error(ex.getMessage());
        // just return empty 404
    }

    private ResponseEntity<Void> entityWithLocation(Object resourceId) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + resourceId.toString()).build().toUri();
        return ResponseEntity.created(uri).build();
    }
}
