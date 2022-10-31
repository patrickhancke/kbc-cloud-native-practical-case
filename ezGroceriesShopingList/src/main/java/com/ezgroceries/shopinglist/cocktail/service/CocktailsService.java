package com.ezgroceries.shopinglist.cocktail.service;

import com.ezgroceries.shopinglist.NotFoundException;
import com.ezgroceries.shopinglist.cocktail.Cocktail;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Controller;

@Controller
public class CocktailsService {
    private static final Map<UUID, Cocktail> cocktails = new HashMap<>();

    static {
        Cocktail cocktail = new Cocktail();
        cocktail.setCocktailId(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"));
        cocktail.setName("Margerita");
        cocktail.setGlass("Cocktail glass");
        cocktail.setInstructions("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..");
        try {
            cocktail.setImage(new URI("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        cocktail.setIngredients(List.of("Tequila",
                "Triple sec",
                "Lime juice",
                "Salt"));
        cocktails.put(cocktail.getCocktailId(), cocktail);

        cocktail = new Cocktail();
        cocktail.setCocktailId(UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"));
        cocktail.setName("Blue Margerita");
        cocktail.setGlass("Cocktail glass");
        cocktail.setInstructions("Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..");
        try {
            cocktail.setImage(new URI("https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        cocktail.setIngredients(List.of("Tequila",
                "Blue Curacao",
                "Lime juice",
                "Salt"));

        cocktails.put(cocktail.getCocktailId(), cocktail);
    }

    public Cocktail getCocktail(UUID cocktailId) {
        Cocktail cocktail = cocktails.get(cocktailId);
        if (cocktail == null)
        {
            throw new NotFoundException("cocktail with id: " + cocktailId + " not found");
        }

        return cocktail;
    }

    public Collection<Cocktail> getCocktails() {
        return cocktails.values();
    }

}
