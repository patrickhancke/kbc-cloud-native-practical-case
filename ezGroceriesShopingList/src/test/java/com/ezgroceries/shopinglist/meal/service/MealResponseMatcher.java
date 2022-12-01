package com.ezgroceries.shopinglist.meal.service;

import static com.ezgroceries.shopinglist.meal.service.MealServiceTest.MEAL_AREA;
import static com.ezgroceries.shopinglist.meal.service.MealServiceTest.MEAL_CATEGORY;
import static com.ezgroceries.shopinglist.meal.service.MealServiceTest.MEAL_IMAGE;
import static com.ezgroceries.shopinglist.meal.service.MealServiceTest.MEAL_INGREDIENT;
import static com.ezgroceries.shopinglist.meal.service.MealServiceTest.MEAL_INSTRUCTIONS;
import static com.ezgroceries.shopinglist.meal.service.MealServiceTest.MEAL_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.ezgroceries.shopinglist.meal.service.MealResponse.MealResource;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class MealResponseMatcher extends BaseMatcher<MealResource> {
    @Override
    public boolean matches(Object actual) {
        MealResource mealResource = (MealResource) actual;
        assertThat(mealResource.getIdMeal(), equalTo("1234"));
        assertThat(mealResource.getStrMeal(), equalTo(MEAL_NAME));
        assertThat(mealResource.getStrCategory(), equalTo(MEAL_CATEGORY));
        assertThat(mealResource.getStrArea(), equalTo(MEAL_AREA));
        assertThat(mealResource.getStrInstructions(), equalTo(MEAL_INSTRUCTIONS));
        assertThat(mealResource.getStrMealThumb(), equalTo(MEAL_IMAGE));
        assertThat(mealResource.getStrIngredient1(), equalTo(MEAL_INGREDIENT));
        return true;
    }

    @Override
    public void describeTo(Description description) {

    }
}
