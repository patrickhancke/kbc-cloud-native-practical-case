package com.ezgroceries.shopinglist.cocktail.service;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import java.net.URI;

public class CocktailTransformer {
    public static Cocktail transform(CocktailEntity entity) {
        Cocktail result = new Cocktail();
        result.setCocktailId(entity.getId());
        result.setName(entity.getName());
        result.setGlass(entity.getGlass());
        result.setInstructions(entity.getInstructions());

        if (entity.getImage() != null) {
            result.setImage(URI.create(entity.getImage()));
        }

        if (entity.getIngredients() != null) {
            result.setIngredients(entity.getIngredients());
        }

        return result;
    }
}
