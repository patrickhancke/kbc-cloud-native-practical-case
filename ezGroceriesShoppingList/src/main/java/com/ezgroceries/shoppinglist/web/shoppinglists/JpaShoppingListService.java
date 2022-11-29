package com.ezgroceries.shoppinglist.web.shoppinglists;


import com.ezgroceries.shoppinglist.web.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.web.cocktails.CocktailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Profile("jpa")
public class JpaShoppingListService implements ShoppingListService {

    private static final Logger log = LoggerFactory.getLogger(JpaShoppingListService.class);

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailRepository cocktailRepository;

    public JpaShoppingListService(ShoppingListRepository shoppingListRepository, CocktailRepository cocktailRepository) {
        log.info("Create service!");
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailRepository = cocktailRepository;
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
    public boolean addCocktailToShoppingList(UUID listId, UUID cocktailId) {
        log.info("Adding cocktail {} to list {}", cocktailId, listId);

        Optional<ShoppingList> shoppingList = getShoppingList(listId);
        Optional<Cocktail> cocktail = cocktailRepository.findById(cocktailId);
        if (shoppingList.isPresent() && cocktail.isPresent()){
            if (shoppingList.get().getCocktails() == null) {
                shoppingList.get().setCocktails(new HashSet<>());
                log.info("empty cocktail list, created it");
            }
            shoppingList.get().getCocktails()
                    .add(cocktail.get());
            shoppingListRepository.save(shoppingList.get());
            log.info("List #cocktails: " + shoppingListRepository.findById(listId).get().getCocktails().size());
            return true;
        }
        else if (!shoppingList.isPresent()) {
            log.info("list not found??");
        }
        else if (!cocktail.isPresent()) {
            log.info("cocktail not found??");
        }
        return false;
    }
}
