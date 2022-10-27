package com.ezgroceries.shoppinglist.config;

import com.ezgroceries.shoppinglist.web.cocktails.CocktailManager;
import com.ezgroceries.shoppinglist.web.cocktails.StubCocktailManager;
import com.ezgroceries.shoppinglist.web.shoppinglists.ShoppingListManager;
import com.ezgroceries.shoppinglist.web.shoppinglists.StubShoppingListManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CocktailManager cocktailManager(){
        return new StubCocktailManager();
    }

    @Bean
    public ShoppingListManager shoppingListManager(){
        return new StubShoppingListManager();
    }
}
