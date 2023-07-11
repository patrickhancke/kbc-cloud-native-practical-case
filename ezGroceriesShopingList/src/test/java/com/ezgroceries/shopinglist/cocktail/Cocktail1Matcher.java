package com.ezgroceries.shopinglist.cocktail;

import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_GLASS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_IMAGE;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_INGREDIENT;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_INGREDIENT2;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_INSTRUCTIONS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_NAME;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_UUID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class Cocktail1Matcher extends BaseMatcher<Cocktail> {

    @Override
    public boolean matches(Object o) {
        Cocktail cocktailEntity = (Cocktail) o;
        assertThat(cocktailEntity.getCocktailId(), equalTo(COCKTAIL_1_UUID));
        assertThat(cocktailEntity.getName(), equalTo(COCKTAIL_1_NAME));
        assertThat(cocktailEntity.getGlass(), equalTo(COCKTAIL_1_GLASS));
        assertThat(cocktailEntity.getImage().toString(), equalTo(COCKTAIL_1_IMAGE));
        assertThat(cocktailEntity.getInstructions(), equalTo(COCKTAIL_1_INSTRUCTIONS));
        assertThat(cocktailEntity.getIngredients(), containsInAnyOrder(COCKTAIL_1_INGREDIENT, COCKTAIL_1_INGREDIENT2));

        return true;
    }

    @Override
    public void describeTo(Description description) {

    }
}
