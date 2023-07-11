package com.ezgroceries.shopinglist.meal.service;

import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_AREA;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_CATEGORY;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_ID;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_IMAGE;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_INGREDIENT;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_INSTRUCTIONS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import com.ezgroceries.shopinglist.meal.web.Meal;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class MealMatcher extends BaseMatcher<Meal> {

    @Override
    public boolean matches(Object o) {
        Meal meal = (Meal) o;
        assertThat(meal.getId(), equalTo(MEAL_ID));
        assertThat(meal.getName(), equalTo(MEAL_NAME));
        assertThat(meal.getImage(), equalTo(MEAL_IMAGE));
        assertThat(meal.getInstructions(), equalTo(MEAL_INSTRUCTIONS));
        assertThat(meal.getCategory(), equalTo(MEAL_CATEGORY));
        assertThat(meal.getArea(), equalTo(MEAL_AREA));
        assertThat(meal.getIngredients(), containsInAnyOrder(MEAL_INGREDIENT));

        return true;
    }

    @Override
    public void describeTo(Description description) {

    }
}
