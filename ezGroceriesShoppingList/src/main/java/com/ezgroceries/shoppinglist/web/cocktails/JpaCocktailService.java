package com.ezgroceries.shoppinglist.web.cocktails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Profile("jpa")
public class JpaCocktailService implements CocktailService {
    private static final Logger log = LoggerFactory.getLogger(JpaCocktailService.class);

    private final CocktailDBClient cocktailDBClient;
    private final CocktailRepository cocktailRepository;

    public JpaCocktailService(CocktailDBClient cocktailDBClient, CocktailRepository cocktailRepository){
        log.info("Create jpacocktailService");
        this.cocktailDBClient = cocktailDBClient;
        this.cocktailRepository = cocktailRepository;
    }

    @Override
    public List<Cocktail> getAllCocktails() {
        log.info("Return all cocktails in DB");
        return cocktailRepository.findAll();
    }

    @Override
    public List<Cocktail> searchCocktail(String search){
        log.info("checking dbClient for {}", search);
        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
        log.info("done with dbClient");
        //The IdDrink ID returned by the api
        List<String> idDrinks = cocktailDBResponse.getDrinks()
                .stream().map(CocktailDBResponse.DrinkResource::getIdDrink)
                .collect(Collectors.toList());
        List<String> idDrinksNotInDb = idDrinks.stream()
                .filter(id -> cocktailRepository.findCocktailsByCocktailId(id).isEmpty())
                .collect(Collectors.toList());

        log.info("idDrinks returned by api {}", idDrinks.size());
        log.info("idDrinks already in the DB {}", cocktailRepository.findAll().size());
        log.info("idDrinks to persist in DB {}", idDrinksNotInDb.size());
        List<Cocktail> cocktailsToPersist = idDrinksNotInDb
                .stream().map(id -> new Cocktail(id, null, null))
                .collect(Collectors.toList());
        cocktailRepository.saveAll(cocktailsToPersist);

        return getCocktailsFromCocktailDBResponse(cocktailDBResponse);
    }

    private List<Cocktail> getCocktailsFromCocktailDBResponse(CocktailDBResponse cocktailDBResponse) {
        log.info("Converting DBResponse to Cocktails");
        List<Cocktail> cocktails = new ArrayList<>();
        for (CocktailDBResponse.DrinkResource drink : cocktailDBResponse.getDrinks()) {
            log.info("new cocktail");
            Cocktail cocktail = new Cocktail();
            cocktail.setCocktailId(drink.getIdDrink());
            cocktail.setName(drink.getStrDrink());
            cocktail.setIngredients(new HashSet<>(List.of(drink.getStrIngredients())));
            cocktails.add(cocktail);
        }
        return cocktails;
    }

}
