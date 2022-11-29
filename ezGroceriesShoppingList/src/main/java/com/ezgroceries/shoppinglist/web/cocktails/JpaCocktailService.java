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
        if (cocktailDBResponse.getDrinks()==null)
            cocktailDBResponse.setDrinks(new ArrayList<>());
        log.info("done with dbClient");
        //Map with IdDrink ID and the drink
        Map<String, CocktailDBResponse.DrinkResource> responseDrinks = cocktailDBResponse.getDrinks()
                .stream()
                .collect(Collectors.toMap(
                        CocktailDBResponse.DrinkResource::getIdDrink,
                        dr -> dr));
        //Check all cocktails in the database and map to key idDrink
        Map<String, Optional<Cocktail>> cocktailsDb = responseDrinks.keySet().stream()
                .collect(Collectors.toMap(
                        id -> id,
                        id -> cocktailRepository.findCocktailsByCocktailId(id).stream().findFirst() //only taking the first cocktail in consideration
                ));
        //Save any cocktails not yet in database
        int existing=0, newones=0;
        for(var entry : cocktailsDb.entrySet()){ //or fancier stream and saveAll
            if (entry.getValue().isEmpty()){
                CocktailDBResponse.DrinkResource drinkResource = responseDrinks.get(entry.getKey());
                Cocktail newcocktail = new Cocktail(entry.getKey(),
                        drinkResource.getStrDrink(),
                        drinkResource.getStrGlass(),
                        drinkResource.getStrInstructions(),
                        drinkResource.getStrImageSource(),
                        new HashSet<>(Arrays.asList(drinkResource.getStrIngredients())));
                entry.setValue(Optional.of(cocktailRepository.save(newcocktail)));
                newones++;
            }
            else {
                existing++;
            }
        }

        log.info("idDrinks returned by api {}", responseDrinks.keySet().size());
        log.info("idDrinks already in the DB {}", existing);
        log.info("idDrinks persisted in DB {}", newones);
        /* Could just return this, but lets check the API response anyway
        return cocktailsDb.values().stream()
                .filter(dr -> dr.isPresent())
                .map(dr -> dr.orElseGet(Cocktail::new))
                .collect(Collectors.toList());
         */
        return getCocktailsFromCocktailDBResponse(responseDrinks, cocktailsDb);
    }

    //this is no longer needed as we persist everything to our database?
    private List<Cocktail> getCocktailsFromCocktailDBResponse(Map<String, CocktailDBResponse.DrinkResource> responseDrinks, Map<String, Optional<Cocktail>> cocktailsDb) {
        log.info("Use the external API reponse and fill the cocktail");
        List<Cocktail> cocktails = new ArrayList<>();
        for (var entry : responseDrinks.entrySet()) {
            Optional<Cocktail> db = cocktailsDb.get(entry.getValue().getIdDrink());
            Cocktail cocktail = db.orElseGet(Cocktail::new);
            cocktail.setCocktailId(entry.getValue().getIdDrink());
            cocktail.setName(entry.getValue().getStrDrink());
            cocktail.setIngredients(new HashSet<>(List.of(entry.getValue().getStrIngredients())));
            cocktail.setGlass(entry.getValue().getStrGlass());
            cocktail.setInstructions(entry.getValue().getStrInstructions());
            cocktails.add(cocktail);
        }
        return cocktails;
    }

}
