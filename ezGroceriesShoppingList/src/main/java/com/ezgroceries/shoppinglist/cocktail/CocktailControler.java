package com.ezgroceries.shoppinglist.cocktail;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CocktailControler {
private final CocktailService cocktailService;
private static final Logger log = LoggerFactory.getLogger(CocktailControler.class);

    public CocktailControler(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping(value="/cocktails")
    public ResponseEntity<List<CocktailDTO>> getCocktails(@RequestParam String search){
        List<CocktailDTO> cocktailDTOList = cocktailService.getCocktails(search);
        log.info("CocktailList for " + search + "found");
        return ResponseEntity.ok(cocktailDTOList);
    }
}
