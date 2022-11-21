package com.ezgroceries.shopinglist.cocktail.service;

import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CocktailFactory {
    static CocktailEntity createCocktail(DrinkResource resource) {
        CocktailEntity cocktailEntity = new CocktailEntity();

        cocktailEntity.setId(UUID.randomUUID());
        cocktailEntity.setIdDrink(resource.getIdDrink());
        cocktailEntity.setIngredients(Stream.of(resource.getStrIngredient1(), resource.getStrIngredient2(), resource.getStrIngredient3())
                .filter(Objects::nonNull)
                .collect(Collectors.toSet()));
        cocktailEntity.setGlass(resource.getStrGlass());
        cocktailEntity.setImage(resource.getStrDrinkThumb());
        cocktailEntity.setInstructions(resource.getStrInstructions());
        cocktailEntity.setName(resource.getStrDrink());

        return cocktailEntity;
    }
}
