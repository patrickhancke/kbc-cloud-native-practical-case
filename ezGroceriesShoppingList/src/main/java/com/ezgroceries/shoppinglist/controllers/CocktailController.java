package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.entities.CocktailResource;
import com.ezgroceries.shoppinglist.services.CocktailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CocktailController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping(value="/cocktails")
    public List<CocktailResource> cocktailSummary(@RequestParam(required = false) String search) {
       logger.info(search);
       if (search != null) {
           return cocktailService.getCocktails(search);

       } else {
           return cocktailService.getAllCocktails();
       }

   }

}
