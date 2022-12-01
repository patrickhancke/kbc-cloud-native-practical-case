package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.entities.CocktailResource;
import com.ezgroceries.shoppinglist.entities.CocktailShoppingListEntity;
import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.repositories.CocktailShoppingListRepository;
import com.ezgroceries.shoppinglist.repositories.ShoppingListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

    private ShoppingListRepository shoppingListRepository;
    private CocktailShoppingListRepository cocktailShoppingListRepository;
    private CocktailService cocktailService;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ShoppingListService(ShoppingListRepository shoppingListRepository, CocktailShoppingListRepository cocktailShoppingListRepository, CocktailService cocktailService) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailShoppingListRepository = cocktailShoppingListRepository;
        this.cocktailService = cocktailService;
    }



    public String createShoppingList(String shoppingListName){
        ShoppingListEntity newShoppingListEntity=new ShoppingListEntity();
        UUID listId = UUID.randomUUID();
        newShoppingListEntity.setId(listId);
        newShoppingListEntity.setName(shoppingListName);
        logger.info("Create new list with id: " + listId + " and with name: " + shoppingListName);
        logger.info(String.valueOf(newShoppingListEntity));
        shoppingListRepository.save(newShoppingListEntity);
        return String.valueOf(listId);
    }

    public void addCocktailToList(UUID shoppingListId,UUID cocktailId){
        CocktailShoppingListEntity newCocktailShoppingListEntity=new CocktailShoppingListEntity();
        newCocktailShoppingListEntity.setShopping_list_id(shoppingListId);
        newCocktailShoppingListEntity.setId(cocktailId);
        logger.info("Add cocktail: " + cocktailId + " to list: " + shoppingListId);
        cocktailShoppingListRepository.save(newCocktailShoppingListEntity);
    }

    public List<CocktailResource> getShoppingList(UUID shoppingListId) {
       List<CocktailShoppingListEntity> newCocktailShoppingListEntity= cocktailShoppingListRepository.findByshoppingListId(shoppingListId);
        CocktailShoppingListEntity test = new CocktailShoppingListEntity();

        List<UUID> ids= newCocktailShoppingListEntity
                .stream()
                .map(CocktailShoppingListEntity::getId)
                .collect(Collectors.toList());

        logger.info("ids = " + ids);

        List<CocktailResource> cocktails = cocktailService.getCocktailList(ids);
        logger.info("cocktails = " + cocktails);
        return cocktails;


    }

    public List<CocktailResource> getAllShoppingLists(){

        List<CocktailShoppingListEntity> newCocktailShoppingListEntity= cocktailShoppingListRepository.findAll();
        CocktailShoppingListEntity test = new CocktailShoppingListEntity();

        List<UUID> ids= newCocktailShoppingListEntity
                .stream()
                .map(CocktailShoppingListEntity::getId)
                .collect(Collectors.toList());

        logger.info("ids = " + ids);

        List<CocktailResource> cocktails = cocktailService.getCocktailList(ids);
        logger.info("cocktails = " + cocktails);
        return cocktails;
    }




}
