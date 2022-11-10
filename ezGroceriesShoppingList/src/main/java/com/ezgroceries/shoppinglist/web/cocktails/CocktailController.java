package com.ezgroceries.shoppinglist.web.cocktails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CocktailController {
    private static final Logger log = LoggerFactory.getLogger(CocktailController.class);
    private final CocktailManager cocktailManager;


    public CocktailController(CocktailManager cocktailManager){
        this.cocktailManager = cocktailManager;
    }


    @GetMapping(value = "/cocktails")
    public ResponseEntity<List<Cocktail>> getCocktails(@RequestParam String search) {

        log.info("cocktail search for " + search);

        List<Cocktail> result = cocktailManager.searchCocktail(search);
        return ResponseEntity.ok().body(result);
    }
}
