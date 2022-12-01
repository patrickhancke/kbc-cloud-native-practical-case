package com.ezgroceries.shoppinglist.managers;

import com.ezgroceries.shoppinglist.entities.CocktailResource;
import com.ezgroceries.shoppinglist.entities.CocktailDBResponse;
import com.ezgroceries.shoppinglist.controllers.CocktailDBClient;
import com.ezgroceries.shoppinglist.services.CocktailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CocktailManager_old {

    @Autowired
    private CocktailDBClient cocktailDBClient;

    private CocktailService cocktailService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static CocktailResource save(CocktailResource newCocktail) {
        return newCocktail;
    }

    public List<CocktailResource> getCocktails(String Cocktail){

        List<CocktailResource> result = new ArrayList<>();
        logger.info("GetCocktails");

        CocktailDBResponse Cocktails = cocktailDBClient.searchCocktails(Cocktail);
        List <CocktailDBResponse.DrinkResource> cocktailList = Cocktails.getDrinks();

        logger.info("I have the list");

        int i = 0;

   /*     for (CocktailDBResponse.DrinkResource a : cocktailList) {
            i++;
           // logger.info("for " + i + ": " + String.valueOf(a));
            result.add(new Cocktail(a.getIdDrink(),a.getStrDrink(),
                    a.getStrGlass(),a.getStrInstructions(),
                    a.getStrDrinkThumb(), Arrays.asList(a.getStrIngredient1(),a.getStrIngredient2(),a.getStrIngredient3())));
        }*/

        logger.info("ok");


        logger.info("ok2");
        result =  cocktailService.mergeCocktails(cocktailList);

        return result;
    }


    public Arrays findByIdDrinkIn(List<String> ids) {
        return (Arrays) ids;
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
