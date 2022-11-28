package com.ezgroceries.shoppinglist.web.cocktails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
@Profile("site")
public class SiteCocktailService implements CocktailService {
    private static final Logger log = LoggerFactory.getLogger(SiteCocktailService.class);

    private final CocktailDBClient cocktailDBClient;

    public SiteCocktailService(CocktailDBClient cocktailDBClient){
        this.cocktailDBClient = cocktailDBClient;
    }
    //Hardcoded values
    private final Set<String> stubIngredients = new HashSet<>(Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt"));
    private final Cocktail margerita = new Cocktail("23b3d85a-3928-41c0-a533-6538a71e17c4", "Margerita",
            "Cocktail glass", "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
            "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
            stubIngredients);

    private final Cocktail blueMargerita = new Cocktail("d615ec78-fe93-467b-8d26-5d26d8eab073", "Blue Margerita", "Cocktail glass",
            "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
            "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
            stubIngredients);


    @Override
    public List<Cocktail> getAllCocktails() {
        List<Cocktail> result = new ArrayList<>();
        result.add(margerita);
        result.add(blueMargerita);
        return result;
    }

    @Override
    public List<Cocktail> searchCocktail(String search){
        log.info("checking dbClient");
        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
        log.info("done with dbClient");
        return getCocktailsFromCocktailDBResponse(cocktailDBResponse);
    }

    private List<Cocktail> getCocktailsFromCocktailDBResponse(CocktailDBResponse cocktailDBResponse) {
        log.info("Converting DBResponse to Cocktails");
        List<Cocktail> cocktails = new ArrayList<>();
        for (CocktailDBResponse.DrinkResource drink : cocktailDBResponse.getDrinks()) {
            log.info("new cocktail");
            Cocktail cocktail = new Cocktail();
            cocktail.setCocktailId(drink.getIdDrink());
            cocktail.setName(drink.getStrDrink());
            cocktail.setIngredients(new HashSet<>(List.of(drink.getStrIngredients())));
            cocktails.add(cocktail);
        }
        return cocktails;
    }

}
