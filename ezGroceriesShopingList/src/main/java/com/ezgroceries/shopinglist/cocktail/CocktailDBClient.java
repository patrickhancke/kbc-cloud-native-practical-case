package com.ezgroceries.shopinglist.cocktail;

import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse.DrinkResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1")
public interface CocktailDBClient {

    @GetMapping(value = "search.php")
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);

    @GetMapping(value = "/lookup.php")
    DrinkResource getById(@RequestParam("i") String id);

}
