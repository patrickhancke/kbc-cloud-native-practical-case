package com.ezgroceries.shopinglist.cocktail.web;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.service.CocktailsService;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CocktailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CocktailsController.class);

    private final CocktailsService cocktailsService;

    public CocktailsController(CocktailsService cocktailsService) {
        this.cocktailsService = cocktailsService;
    }

    @ResponseBody
    @GetMapping("/cocktails")
    public Collection<Cocktail> getCocktails(@RequestParam(value = "searchTerm", required = false) String searchTerm) {
        LOGGER.info("searchTerm: " + searchTerm);

        return cocktailsService.getCocktails();
    }
}
