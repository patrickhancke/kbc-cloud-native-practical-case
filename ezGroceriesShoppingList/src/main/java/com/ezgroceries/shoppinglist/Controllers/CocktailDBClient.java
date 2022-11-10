package com.ezgroceries.shoppinglist.Controllers;


import com.ezgroceries.shoppinglist.Classes.CocktailDBResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1")
public interface CocktailDBClient {
  @GetMapping(value = "search.php")
    public CocktailDBResponse searchCocktails(@RequestParam("s") String search);

}