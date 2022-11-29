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
        //Check all cocktails in the database and map to key idDrink
        Map<String, Optional<Cocktail>> cocktailsDb = idDrinks.stream()
                .collect(Collectors.toMap(
                        id -> id,
                        id -> cocktailRepository.findCocktailsByCocktailId(id).stream().findFirst() //only taking the first cocktail in consideration
                ));
        //Save any cocktails not yet in database
        int existing=0, newones=0;
        for(var entry : cocktailsDb.entrySet()){ //or fancier stream and saveAll
            if (entry.getValue().isEmpty()){
                Cocktail newcocktail = new Cocktail(entry.getKey(), null, null);
                entry.setValue(Optional.of(cocktailRepository.save(newcocktail)));
                newones++;
            }
            else {
                existing++;
            }
        }

        log.info("idDrinks returned by api {}", idDrinks.size());
        log.info("idDrinks already in the DB {}", existing);
        log.info("idDrinks persisted in DB {}", newones);

        return getCocktailsFromCocktailDBResponse(cocktailDBResponse, cocktailsDb);
    }

    private List<Cocktail> getCocktailsFromCocktailDBResponse(CocktailDBResponse cocktailDBResponse, Map<String, Optional<Cocktail>> cocktailsDb) {
        log.info("Converting DBResponse to Cocktails");
        List<Cocktail> cocktails = new ArrayList<>();
        for (CocktailDBResponse.DrinkResource drink : cocktailDBResponse.getDrinks()) {
            log.info("new cocktail");
            Optional<Cocktail> db = cocktailsDb.get(drink.getIdDrink());
            Cocktail cocktail = db.orElseGet(Cocktail::new);
            cocktail.setCocktailId(drink.getIdDrink());
            cocktail.setName(drink.getStrDrink());
            cocktail.setIngredients(new HashSet<>(List.of(drink.getStrIngredients())));
            cocktail.setGlass(drink.getStrGlass());
            cocktails.add(cocktail);
        }
        return cocktails;
    }

}
