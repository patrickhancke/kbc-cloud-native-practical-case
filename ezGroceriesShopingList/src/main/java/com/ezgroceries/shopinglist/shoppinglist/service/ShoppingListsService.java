package com.ezgroceries.shopinglist.shoppinglist.service;

import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shopinglist.cocktail.service.CocktailTransformer;
import com.ezgroceries.shopinglist.cocktail.service.CocktailService;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesBadRequestException;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesNotFoundException;
import com.ezgroceries.shopinglist.shoppinglist.ShoppingList;
import com.ezgroceries.shopinglist.shoppinglist.persistence.ShoppingListEntity;
import com.ezgroceries.shopinglist.shoppinglist.persistence.ShoppingListRepository;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListsService.class);
    private final ShoppingListRepository shoppingListRepository;
    private final CocktailService cocktailService;

    public ShoppingListsService(ShoppingListRepository shoppingListRepository, CocktailService cocktailService) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailService = cocktailService;
    }

    public ShoppingList getShoppingList(UUID id) {
        if (id == null) {
            throw new EzGroceriesBadRequestException("id required");
        }
        Optional<ShoppingListEntity> shoppingList = shoppingListRepository.findById(id);
        if (shoppingList.isEmpty()) {
            throw new EzGroceriesNotFoundException("shopping list with id: " + id + " not found");
        }
        if (!Objects.equals(getLoggedUsername(), shoppingList.get().getUsername())) {
            throw new EzGroceriesNotFoundException("shopping list with id: " + id + " not found");
        }

        return transform(shoppingList.get());
    }

    public int addCocktailToShoppingList(UUID shoppingListId, UUID cocktailId) {
        if (shoppingListId == null || cocktailId == null) {
            throw new EzGroceriesBadRequestException("id required!");
        }

        Optional<ShoppingListEntity> shoppingList = shoppingListRepository.findById(shoppingListId);
        if (shoppingList.isEmpty()) {
            throw new EzGroceriesNotFoundException("shopping list with id: " + shoppingListId + " not found");
        }
        CocktailEntity cocktail = cocktailService.getCocktailEntity(cocktailId);
        LOGGER.debug("cocktail: {}", cocktail);
        shoppingList.get().addCocktail(cocktail);
        shoppingListRepository.save(shoppingList.get());

        return 1;
    }

    public ShoppingList createShoppingList(String name) {

        ShoppingListEntity shoppingList = new ShoppingListEntity();
        shoppingList.setId(UUID.randomUUID());
        shoppingList.setName(name);
        shoppingList.setUsername(getLoggedUsername());
        return transform(shoppingListRepository.save(shoppingList));
    }

    private ShoppingList transform(ShoppingListEntity entity) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setShoppingListId(entity.getId());
        shoppingList.setName(entity.getName());

        Set<CocktailEntity> cocktails = entity.getCocktails();
        if (cocktails != null) {
            shoppingList.setCocktails(cocktails.stream().map(CocktailTransformer::transform).collect(Collectors.toList()));
            shoppingList.setIngredients(entity.getCocktails().stream().flatMap(element -> element.getIngredients().stream()).collect(Collectors.toSet()));
        }

        return shoppingList;
    }

    private String getLoggedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
        {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return null;
    }
}
