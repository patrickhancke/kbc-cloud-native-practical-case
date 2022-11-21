package com.ezgroceries.shopinglist.cocktail;

import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_GLASS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_IMAGE;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_INGREDIENT;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_INGREDIENT2;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_INSTRUCTIONS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_NAME;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_UUID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class Cocktail3Matcher extends BaseMatcher<Cocktail> {

    @Override
    public boolean matches(Object o) {
        Cocktail cocktailEntity = (Cocktail) o;
        assertThat(cocktailEntity.getCocktailId(), equalTo(COCKTAIL_3_UUID));
        assertThat(cocktailEntity.getName(), equalTo(COCKTAIL_3_NAME));
        assertThat(cocktailEntity.getGlass(), equalTo(COCKTAIL_3_GLASS));
        assertThat(cocktailEntity.getImage(), equalTo(COCKTAIL_3_IMAGE));
        assertThat(cocktailEntity.getInstructions(), equalTo(COCKTAIL_3_INSTRUCTIONS));
        assertThat(cocktailEntity.getIngredients(), containsInAnyOrder(COCKTAIL_3_INGREDIENT, COCKTAIL_3_INGREDIENT2));

        return true;
    }

    @Override
    public void describeTo(Description description) {

    }
}
