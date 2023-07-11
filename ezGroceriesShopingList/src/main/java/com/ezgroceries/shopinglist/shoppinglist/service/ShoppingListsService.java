package com.ezgroceries.shopinglist.shoppinglist.service;

import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shopinglist.cocktail.service.CocktailService;
import com.ezgroceries.shopinglist.cocktail.service.CocktailTransformer;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesBadRequestException;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesNotFoundException;
import com.ezgroceries.shopinglist.meal.persistence.MealEntity;
import com.ezgroceries.shopinglist.meal.persistence.MealRepository;
import com.ezgroceries.shopinglist.meal.web.MealTransformer;
import com.ezgroceries.shopinglist.shoppinglist.ShoppingList;
import com.ezgroceries.shopinglist.shoppinglist.persistence.ShoppingListEntity;
import com.ezgroceries.shopinglist.shoppinglist.persistence.ShoppingListRepository;
import java.util.HashSet;
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
    private final MealRepository mealRepository;
    private final CocktailService cocktailService;

    public ShoppingListsService(ShoppingListRepository shoppingListRepository,
            CocktailService cocktailService,
            MealRepository mealRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailService = cocktailService;
        this.mealRepository = mealRepository;
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

    public void addCocktailToShoppingList(UUID shoppingListId, UUID cocktailId) {
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
    }

    public void addMealToShoppingList(UUID shoppingListId, UUID mealId) {
        if (shoppingListId == null || mealId == null)
        {
            throw new EzGroceriesBadRequestException("id required!");
        }

        Optional<ShoppingListEntity> shoppingList = shoppingListRepository.findById(shoppingListId);
        if (shoppingList.isEmpty()) {
            throw new EzGroceriesNotFoundException("shopping list with id: " + shoppingListId + " not found");
        }

        Optional<MealEntity> byId = mealRepository.findById(mealId);
        if (byId.isEmpty()) {
            throw new EzGroceriesNotFoundException("meal with id: " + mealId + " not found");
        }
        MealEntity mealEntity = byId.get();
        LOGGER.debug("meal: {}", mealEntity);
        shoppingList.get().addMeal(mealEntity);
        shoppingListRepository.save(shoppingList.get());
    }

    public ShoppingList createShoppingList(String name) {

        ShoppingListEntity shoppingList = new ShoppingListEntity();
        shoppingList.setId(UUID.randomUUID());
        shoppingList.setName(name);
        shoppingList.setUsername(getLoggedUsername());
        return transform(shoppingListRepository.save(shoppingList));
    }

    private ShoppingList transform(ShoppingListEntity entity) {
        var shoppingList = new ShoppingList();
        shoppingList.setShoppingListId(entity.getId());
        shoppingList.setName(entity.getName());
        Set<String> ingredients = new HashSet<>();

        var cocktails = entity.getCocktails();
        if (cocktails != null) {
            shoppingList.setCocktails(cocktails.stream().filter(Objects::nonNull).map(CocktailTransformer::transform).collect(Collectors.toList()));
            ingredients.addAll(entity.getCocktails().stream().filter(Objects::nonNull).flatMap(element -> element.getIngredients().stream()).collect(Collectors.toSet()));
        }

        var meals = entity.getMeals();
        if (meals != null) {
            shoppingList.setMeals(meals.stream().map(MealTransformer.getInstance()::transform).collect(Collectors.toList()));
            ingredients.addAll(entity.getMeals().stream().filter(Objects::nonNull).flatMap(element -> element.getIngredients().stream()).collect(Collectors.toSet()));
        }

        shoppingList.setIngredients(ingredients);
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
