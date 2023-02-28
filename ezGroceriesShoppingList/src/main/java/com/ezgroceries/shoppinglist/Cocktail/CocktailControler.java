package com.ezgroceries.shoppinglist.Cocktail;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CocktailControler {
private final CocktailService cocktailService;

    public CocktailControler(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping(value="/cocktails")
    public @ResponseBody List<CocktailDTO> getCocktails(@RequestParam String search){
        List<CocktailDTO> cocktailDTOList = cocktailService.getCocktails(search);
        return cocktailDTOList;
    }
}
