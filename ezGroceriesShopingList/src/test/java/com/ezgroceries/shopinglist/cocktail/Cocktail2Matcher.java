package com.ezgroceries.shopinglist.cocktail;

import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_UUID;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_NAME;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_IMAGE;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_GLASS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_INSTRUCTIONS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_INGREDIENT;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_INGREDIENT2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class Cocktail2Matcher extends BaseMatcher<Cocktail> {

    @Override
    public boolean matches(Object o) {
        Cocktail cocktailEntity = (Cocktail) o;
        assertThat(cocktailEntity.getCocktailId(), equalTo(COCKTAIL_2_UUID));
        assertThat(cocktailEntity.getName(), equalTo(COCKTAIL_2_NAME));
        assertThat(cocktailEntity.getGlass(), equalTo(COCKTAIL_2_GLASS));
        assertThat(cocktailEntity.getImage().toString(), equalTo(COCKTAIL_2_IMAGE));
        assertThat(cocktailEntity.getInstructions(), equalTo(COCKTAIL_2_INSTRUCTIONS));
        assertThat(cocktailEntity.getIngredients(), containsInAnyOrder(COCKTAIL_2_INGREDIENT, COCKTAIL_2_INGREDIENT2));

        return true;
    }

    @Override
    public void describeTo(Description description) {

    }
}
