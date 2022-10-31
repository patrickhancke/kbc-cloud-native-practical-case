package com.ezgroceries.shopinglist.cocktail.web;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.CocktailDBClient;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shopinglist.cocktail.service.CocktailsService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CocktailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CocktailsController.class);

    private final CocktailDBClient cocktailDBClient;

    public CocktailsController(CocktailDBClient cocktailDBClient) {
        this.cocktailDBClient = cocktailDBClient;
    }

    @ResponseBody
    @GetMapping("/cocktails")
    public Collection<Cocktail> getCocktails(@RequestParam(value = "searchTerm", required = false) String searchTerm) {
        LOGGER.info("searchTerm: " + searchTerm);
        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(searchTerm);

        return cocktailDBResponse.getDrinks().stream().map(this::transform).collect(Collectors.toList());
    }

    private Cocktail transform(DrinkResource resource) {
        Cocktail cocktail = new Cocktail();
        if (resource == null) {
            return cocktail;
        }

        cocktail.setCocktailId(UUID.fromString(resource.getIdDrink()));
        cocktail.setName(resource.getStrDrink());
        cocktail.setGlass(resource.getStrGlass());
        try {
            URI uri = new URI(resource.getStrDrinkThumb());
            cocktail.setImage(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        cocktail.setInstructions(resource.getStrInstructions());
        cocktail.getIngredients().add(resource.getStrIngredient1());
        cocktail.getIngredients().add(resource.getStrIngredient2());
        cocktail.getIngredients().add(resource.getStrIngredient3());

        return cocktail;
    }
}
