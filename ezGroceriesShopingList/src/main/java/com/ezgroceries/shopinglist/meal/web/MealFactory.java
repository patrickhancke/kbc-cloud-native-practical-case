package com.ezgroceries.shopinglist.meal.web;

import com.ezgroceries.shopinglist.meal.persistence.MealEntity;
import com.ezgroceries.shopinglist.meal.service.MealResponse.MealResource;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MealFactory {
    private static MealFactory instance;

    public static MealFactory getInstance() {
        if (instance == null) {
            instance = new MealFactory();
        }

        return instance;
    }

    private MealFactory() {
        //Singleton
    }

    public MealEntity create(MealResource resource) {
        MealEntity meal = new MealEntity();
        meal.setId(UUID.randomUUID());
        meal.setIdMeal(resource.getIdMeal());
        meal.setName(resource.getStrMeal());
        meal.setImage(resource.getStrMealThumb());
        meal.setInstructions(resource.getStrInstructions());
        meal.setCategory(resource.getStrCategory());
        meal.setArea(resource.getStrArea());
        meal.setIngredients(Stream.of(resource.getStrIngredient1(),
                resource.getStrIngredient2(),
                resource.getStrIngredient3()).collect(Collectors.toSet()));

        return meal;
    }
}
