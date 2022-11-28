package com.ezgroceries.shoppinglist.web.shoppinglists;


import com.ezgroceries.shoppinglist.web.cocktails.Cocktail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Profile("jpa")
public class JpaShoppingListService implements ShoppingListService {

    private static final Logger log = LoggerFactory.getLogger(JpaShoppingListService.class);

    private final ShoppingListRepository shoppingListRepository;

    public JpaShoppingListService(ShoppingListRepository shoppingListRepository) {
        log.info("Create service!");
        this.shoppingListRepository = shoppingListRepository;
    }

    public List<ShoppingList> getAllShoppingLists() {

        List<ShoppingList> entities = shoppingListRepository.findAll();
        log.info("Shoppinglists entities found by service {}", entities.size());

        return entities;
    }

    @Override
    public Optional<ShoppingList> getShoppingList(UUID id) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(id);
        return shoppingList;
    }

    @Override
    public UUID createShoppingList(String name) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(name);
        shoppingListRepository.save(shoppingList);
        return shoppingList.getId();
    }

    @Override
    public void addCocktailToShoppingList(UUID listId, Cocktail cocktail) {
        log.info("Adding cocktail {} to list {}", cocktail.getCocktailId(), listId);
        //TODO
    }
}
