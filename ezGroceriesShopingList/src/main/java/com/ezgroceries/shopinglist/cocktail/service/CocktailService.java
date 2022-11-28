package com.ezgroceries.shopinglist.cocktail.service;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailRepository;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CocktailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CocktailService.class);
    private final CocktailRepository cocktailRepository;
    private final CocktailDBClient client;

    public CocktailService(CocktailRepository cocktailRepository, CocktailDBClient client) {
        this.cocktailRepository = cocktailRepository;
        this.client = client;
    }

    public CocktailEntity getCocktailEntity(UUID cocktailId) {
        Optional<CocktailEntity> cocktail = cocktailRepository.findById(cocktailId);
        if (cocktail.isEmpty()) {
            throw new EzGroceriesNotFoundException("cocktail with id: " + cocktailId + " not found");
        }

        return cocktail.get();
    }

    public Collection<Cocktail> searchByTerm(String searchTerm) {
        CocktailDBResponse cocktailDBResponse = client.searchCocktails(searchTerm);
        if (cocktailDBResponse == null) {
            return Collections.emptyList();
        }

        Collection<CocktailEntity> byIdDrinkIn = cocktailRepository.findByIdDrinkIn(
                cocktailDBResponse.getDrinks().stream().map(DrinkResource::getIdDrink).collect(Collectors.toList()));

        if (Objects.equals(byIdDrinkIn.size(), cocktailDBResponse.getDrinks().size())) {
            return byIdDrinkIn.stream().map(CocktailTransformer::transform).collect(Collectors.toList());
        }
        List<String> existingDrinkIds = byIdDrinkIn.stream().map(CocktailEntity::getIdDrink).collect(Collectors.toList());
        Collection<CocktailEntity> cocktailEntities = populateCocktailTable(cocktailDBResponse.getDrinks().stream()
                .filter(cocktail -> !existingDrinkIds.contains(cocktail.getIdDrink()))
                .collect(Collectors.toList()));
        cocktailEntities.addAll(byIdDrinkIn);
        return cocktailEntities.stream().map(CocktailTransformer::transform).collect(Collectors.toList());
    }

    private Collection<CocktailEntity> populateCocktailTable(Collection<DrinkResource> source) {
        List<CocktailEntity> entities = source.stream().map(CocktailFactory::createCocktail).collect(Collectors.toList());
        Collection<CocktailEntity> cocktailEntities = (Collection<CocktailEntity>) cocktailRepository.saveAll(entities);
        LOGGER.debug("cocktail table populated with {}", cocktailEntities.size());

        return cocktailEntities;
    }
}
