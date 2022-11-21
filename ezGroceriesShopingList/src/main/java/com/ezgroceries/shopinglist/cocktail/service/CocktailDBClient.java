package com.ezgroceries.shopinglist.cocktail.service;

import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailRepository;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1", fallback = CocktailDBClient.CocktailDBClientFallback.class)
public interface CocktailDBClient {

    @GetMapping(value = "search.php")
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);

    @Component
    class CocktailDBClientFallback implements CocktailDBClient {

        private final CocktailRepository cocktailRepository;

        public CocktailDBClientFallback(CocktailRepository cocktailRepository) {
            this.cocktailRepository = cocktailRepository;
        }

        @Override
        public CocktailDBResponse searchCocktails(String search) {
            Collection<CocktailEntity> cocktailEntities = cocktailRepository.findByNameContainingIgnoreCase(search);

            CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();
            cocktailDBResponse.setDrinks(cocktailEntities.stream().map(cocktailEntity -> {
                CocktailDBResponse.DrinkResource drinkResource = new CocktailDBResponse.DrinkResource();
                drinkResource.setIdDrink(cocktailEntity.getIdDrink());
                drinkResource.setStrDrink(cocktailEntity.getName());
                drinkResource.setStrDrinkThumb(cocktailEntity.getImage());
                drinkResource.setStrGlass(cocktailEntity.getGlass());
                Set<String> ingredients = cocktailEntity.getIngredients();
                if (ingredients != null && !ingredients.isEmpty()) {
                    Iterator<String> ingredientIterator = ingredients.iterator();
                    if (ingredientIterator.hasNext()) {
                        drinkResource.setStrIngredient1(ingredientIterator.next());
                    }
                    if (ingredientIterator.hasNext()) {
                        drinkResource.setStrIngredient2(ingredientIterator.next());
                    }
                    if (ingredientIterator.hasNext()) {
                        drinkResource.setStrIngredient3(ingredientIterator.next());
                    }
                }
                return drinkResource;
            }).collect(Collectors.toList()));

            return cocktailDBResponse;
        }
    }

}
