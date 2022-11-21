package com.ezgroceries.shopinglist.cocktail.service;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CocktailTransformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CocktailTransformer.class);

    public static Cocktail transform(CocktailEntity entity) {
        Cocktail result = new Cocktail();
        result.setCocktailId(entity.getId());
        result.setName(entity.getName());
        result.setGlass(entity.getGlass());
        result.setInstructions(entity.getInstructions());

        if (entity.getImage() != null) {
            try {
                result.setImage(new URI(entity.getImage()));
            } catch (URISyntaxException e) {
                LOGGER.error(e.getMessage());
            }
        }

        if (entity.getIngredients() != null) {
            result.setIngredients(entity.getIngredients());
        }

        return result;
    }
}
