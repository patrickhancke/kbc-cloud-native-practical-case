package com.ezgroceries.shoppinglist.services;
import com.ezgroceries.shoppinglist.controllers.CocktailDBClient;
import com.ezgroceries.shoppinglist.converters.StringSetConverter;
import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.entities.CocktailResource;
import com.ezgroceries.shoppinglist.entities.CocktailDBResponse;
import com.ezgroceries.shoppinglist.repositories.CocktailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.Convert;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CocktailService {

    private CocktailDBClient cocktailDBClient;
    private CocktailRepository cocktailRepository;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public CocktailService(CocktailDBClient cocktailDBClient, CocktailRepository cocktailRepository) {
        this.cocktailDBClient = cocktailDBClient;
        this.cocktailRepository = cocktailRepository;
    }

    public static CocktailResource save(CocktailResource newCocktail) {
        return newCocktail;
    }

    public List<CocktailResource> getCocktails(String Cocktail){

        List<CocktailResource> result = new ArrayList<>();
        logger.info("GetCocktails");

        CocktailDBResponse Cocktails = cocktailDBClient.searchCocktails(Cocktail);
        List <CocktailDBResponse.DrinkResource> cocktailList = Cocktails.getDrinks();

        logger.info("I have the list");

        result =  mergeCocktails(cocktailList);

        return result;
    }

    public List<CocktailResource> mergeCocktails(List<CocktailDBResponse.DrinkResource>drinks){
        logger.info ("Get all the idDrink attributes");
        List<String> ids=drinks
                .stream()
                .map(CocktailDBResponse.DrinkResource::getIdDrink)
                .collect(Collectors.toList());
        logger.info(String.valueOf(ids));

        logger.info("Get all the ones we already have from our DB, use a Map for convenient lookup");
        Map<String, CocktailEntity>  existingEntityMap= (cocktailRepository.findByIdDrinkIn(ids))
                .stream()
                .collect(Collectors.toMap(CocktailEntity::getIdDrink, o->o,(o, o2)->o));

        logger.info(String.valueOf(existingEntityMap));

        logger.info("Stream over all the drinks, map them to the existing ones, persist a new one if not existing");
        Map<String, CocktailEntity> allEntityMap=drinks.stream().map(drinkResource->{
            CocktailEntity cocktailEntity=existingEntityMap.get(drinkResource.getIdDrink());
            if(cocktailEntity==null){
                CocktailEntity newCocktailEntity=new CocktailEntity();
                newCocktailEntity.setCocktailId(UUID.randomUUID());
                newCocktailEntity.setIdDrink(drinkResource.getIdDrink());
                logger.info(newCocktailEntity.getIdDrink());
                newCocktailEntity.setName(drinkResource.getStrDrink());
                logger.info(String.valueOf(newCocktailEntity.getName()));
            //   newCocktailEntity.setIngredients(drinkResource.getIngredients());
           //     logger.info(String.valueOf(newCocktailEntity.getIngredients()));
                cocktailEntity=cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getIdDrink,o->o,(o,o2)->o));

        logger.info("Merge drinks and our entities, transform to CocktailResource instances");
        return mergeAndTransform(drinks,allEntityMap);
    }

    private List<CocktailResource> mergeAndTransform(List<CocktailDBResponse.DrinkResource>drinks,Map<String, CocktailEntity> allEntityMap){
        return drinks.stream().map(drinkResource->new CocktailResource(allEntityMap.get(drinkResource.getIdDrink()).getCocktailId(),drinkResource.getStrDrink(),drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(),drinkResource.getStrDrinkThumb(),drinkResource.getIngredients(drinkResource))).collect(Collectors.toList());
    }

    public List<CocktailResource> getCocktailList(List<UUID> ids){

        logger.info(String.valueOf(ids));
        Map<String, CocktailEntity>  EntityMap= (cocktailRepository.findByIdIn(ids))
                .stream()
                .collect(Collectors.toMap(CocktailEntity::getIdDrink, o->o,(o, o2)->o));

        logger.info(String.valueOf(EntityMap));

        List<CocktailResource> cocktails = new ArrayList<>();

       EntityMap.entrySet().forEach((entry) -> cocktails.add(new CocktailResource(String.valueOf(entry.getValue().getCocktailId()),entry.getValue().getIdDrink(),entry.getValue().getName())));
        logger.info(String.valueOf(cocktails));
        return cocktails;

    }


    public List<CocktailResource> getAllCocktails(){

        List<CocktailResource> result = new ArrayList<>();
    /*    result.add(new Cocktail("23b3d85a-3928-41c0-a533-6538a71e17c4","Margerita",
                "Cocktail glass","Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg", Arrays.asList("Tequila","Triple sec","Lime juice","Salt")));
        result.add(new Cocktail("d615ec78-fe93-467b-8d26-5d26d8eab073","Blue Margerita",
                "Cocktail glass","Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg", Arrays.asList("Tequila","Blue Curacao","Lime juice","Salt")));*/
        return result;

    }


}




