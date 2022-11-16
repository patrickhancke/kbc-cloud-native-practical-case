package com.ezgroceries.shopinglist.shoppinglist.service;

import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shopinglist.cocktail.service.CocktailsService;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesNotFoundException;
import com.ezgroceries.shopinglist.shoppinglist.ShoppingList;
import com.ezgroceries.shopinglist.shoppinglist.persistence.ShoppingListEntity;
import com.ezgroceries.shopinglist.shoppinglist.persistence.ShoppingListRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class ShoppingListsService {

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailsService cocktailsService;

    public ShoppingListsService(ShoppingListRepository shoppingListRepository, CocktailsService cocktailsService) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailsService = cocktailsService;
    }

    public ShoppingList getShoppingList(UUID id) {
        Optional<ShoppingListEntity> shoppingList = shoppingListRepository.findById(id);
        if (shoppingList.isEmpty()) {
            throw new EzGroceriesNotFoundException("shopping list with id: " + id + " not found");
        }

        return transform(shoppingList.get());
    }

    public int update(UUID shoppingListId, UUID cocktailId) {
        Optional<ShoppingListEntity> shoppingList = shoppingListRepository.findById(shoppingListId);
        if (shoppingList.isEmpty()) {
            throw new EzGroceriesNotFoundException("shopping list with id: " + shoppingListId + " not found");
        }
        CocktailEntity cocktail = cocktailsService.getCocktailEntity(cocktailId);
        LoggerFactory.getLogger(this.getClass()).error("cocktail: " + cocktail);
        shoppingList.get().getCocktails().add(cocktail);
        shoppingListRepository.save(shoppingList.get());

        return 1;
    }

    public ShoppingList createShoppingList(String name) {

        ShoppingListEntity shoppingList = new ShoppingListEntity();
        shoppingList.setId(UUID.randomUUID());
        shoppingList.setName(name);
        return transform(shoppingListRepository.save(shoppingList));
    }

    private ShoppingList transform(ShoppingListEntity entity) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setShoppingListId(entity.getId());
        shoppingList.setName(entity.getName());

        shoppingList.setCocktails(entity.getCocktails().stream().map(cocktailsService::transform).collect(Collectors.toList()));
        shoppingList.setIngredients(shoppingList.getCocktails().stream().flatMap(element -> element.getIngredients().stream()).collect(Collectors.toSet()));

        return shoppingList;
    }
}
