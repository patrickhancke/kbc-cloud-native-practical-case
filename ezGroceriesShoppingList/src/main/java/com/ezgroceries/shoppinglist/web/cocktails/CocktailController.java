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
    private final CocktailService cocktailService;


    public CocktailController(CocktailService cocktailService){
        log.info("Create CocktailController");
        this.cocktailService = cocktailService;
    }


    @GetMapping(value = "/cocktails")
    public ResponseEntity<List<Cocktail>> getCocktails(@RequestParam(required = false) String search) {

        log.info("cocktail search for {}", search);
        List<Cocktail> result;
        if (search==null)
            result = cocktailService.getAllCocktails();
        else
            result = cocktailService.searchCocktail(search);
        return ResponseEntity.ok().body(result);
    }
}
