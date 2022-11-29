package com.ezgroceries.shoppinglist.web.shoppinglists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Profile("stub")
public class StubShoppingListService implements ShoppingListService {
    private static final Logger log = LoggerFactory.getLogger(StubShoppingListService.class);

    private final ShoppingList stephanieDummyShoppingList = new ShoppingList(UUID.randomUUID(),
            "Stephanie's birthday",
            new String[]{"Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"});

    private final ShoppingList myDummyShoppingList = new ShoppingList(UUID.randomUUID(),
            "My birthday",
            new String[]{"Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"});

    private final UUID dummyListId = UUID.randomUUID();

    @Override
    public List<ShoppingList> getAllShoppingLists() {
        List<ShoppingList> result = new ArrayList<>();
        result.add(stephanieDummyShoppingList);
        result.add(myDummyShoppingList);
        return result;
    }

    @Override
    public Optional<ShoppingList> getShoppingList(UUID id) {
        log.info("returning default stephanie list instead of {}", id);
        return Optional.of(stephanieDummyShoppingList);
    }

    @Override
    public UUID createShoppingList(String name) {
        log.info("doing nothing really..");
        return dummyListId;
    }

    @Override
    public boolean addCocktailToShoppingList(UUID listId, UUID cocktailId) {
        log.info("doing nothing really..");
        return true;
    }
}
