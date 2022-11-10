package com.ezgroceries.shoppinglist.Controllers;

import com.ezgroceries.shoppinglist.Classes.Cocktail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ezgroceries.shoppinglist.Managers.CocktailManager;
import java.util.List;

@RestController
public class CocktailController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CocktailManager cocktailManager;

    @Autowired
    public CocktailController(CocktailManager cocktailManager) {
        this.cocktailManager = cocktailManager;
    }

   @GetMapping(value="/cocktails")
    public List<Cocktail> cocktailSummary(@RequestParam(required = false) String search) {
       logger.info(search);
       if (search != null) {
           return cocktailManager.getCocktails(search);

       } else {
           return cocktailManager.getAllCocktails();
       }

   }

}
