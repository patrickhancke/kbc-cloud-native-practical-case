package com.ezgroceries.shopinglist.meal.web;

import com.ezgroceries.shopinglist.meal.persistence.MealEntity;

public class MealTransformer {
    private static MealTransformer instance;

    public static MealTransformer getInstance() {
        if (instance == null) {
            instance = new MealTransformer();
        }

        return instance;
    }

    private MealTransformer() {
        //Singleton
    }

    public Meal transform(MealEntity entity) {
        Meal meal = new Meal();
        meal.setId(entity.getId());
        meal.setName(entity.getName());
        meal.setImage(entity.getImage());
        meal.setInstructions(entity.getInstructions());
        meal.setCategory(entity.getCategory());
        meal.setArea(entity.getArea());
        meal.setIngredients(entity.getIngredients());

        return meal;
    }
}
