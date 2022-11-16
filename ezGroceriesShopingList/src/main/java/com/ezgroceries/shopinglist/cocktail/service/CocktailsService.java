package com.ezgroceries.shopinglist.cocktail.service;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.CocktailDBClient;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailRepository;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class CocktailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CocktailsService.class);

    private final CocktailRepository cocktailRepository;
    private final CocktailDBClient client;

    public CocktailsService(CocktailRepository cocktailRepository, CocktailDBClient client) {
        this.cocktailRepository = cocktailRepository;
        this.client = client;
    }

    public CocktailEntity getCocktailEntity(UUID cocktailId) {
        Optional<CocktailEntity> cocktail = cocktailRepository.findById(cocktailId);
        if (cocktail.isEmpty())
        {
            throw new EzGroceriesNotFoundException("cocktail with id: " + cocktailId + " not found");
        }

        return cocktail.get();
    }

    public Collection<Cocktail> searchByTerm(String searchTerm) {
        CocktailDBResponse cocktailDBResponse = client.searchCocktails(searchTerm);
        if (cocktailDBResponse == null)
        {
            return Collections.emptyList();
        }

        Collection<CocktailEntity> byIdDrunkIn = cocktailRepository.findByIdDrunkIn(
                cocktailDBResponse.getDrinks().stream().map(DrinkResource::getIdDrink).collect(Collectors.toList()));

        if (Objects.equals(byIdDrunkIn.size(), cocktailDBResponse.getDrinks().size())) {
            return byIdDrunkIn.stream().map(this::transform).collect(Collectors.toList());
        }

        Collection<CocktailEntity> cocktailEntities = populateCocktailTable(cocktailDBResponse.getDrinks().stream()
                .filter(cocktail -> !byIdDrunkIn.stream().map(CocktailEntity::getIdDrunk).collect(Collectors.toList()).contains(cocktail.getIdDrink()))
                .collect(Collectors.toList()));
        cocktailEntities.addAll(byIdDrunkIn);
        return cocktailEntities.stream().map(this::transform).collect(Collectors.toList());
    }

    private Collection<CocktailEntity> populateCocktailTable(Collection<DrinkResource> source) {
        List<CocktailEntity> entities = source.stream().map(this::createCocktail).collect(Collectors.toList());
        Iterable<CocktailEntity> cocktailEntities = cocktailRepository.saveAll(entities);
        LOGGER.debug("cocktail table populated with " + ((Collection<?>)cocktailEntities).size());

        return new ArrayList<>(((Collection<CocktailEntity>) cocktailEntities));
    }

    private CocktailEntity createCocktail(DrinkResource resource) {
        CocktailEntity cocktailEntity = new CocktailEntity();

        cocktailEntity.setId(UUID.randomUUID());
        cocktailEntity.setIdDrunk(resource.getIdDrink());
        cocktailEntity.setIngredients(Stream.of(resource.getStrIngredient1(), resource.getStrIngredient2(), resource.getStrIngredient3())
                .filter(Objects::nonNull)
                .collect(Collectors.toSet()));
        cocktailEntity.setGlass(resource.getStrGlass());
        cocktailEntity.setImage(resource.getStrDrinkThumb());
        cocktailEntity.setInstructions(resource.getStrInstructions());
        cocktailEntity.setName(resource.getStrDrink());

        return cocktailEntity;
    }

    public Cocktail transform(CocktailEntity entity) {
        Cocktail result = new Cocktail();
        result.setCocktailId(entity.getId());
        result.setName(entity.getName());
        result.setGlass(entity.getGlass());
        result.setInstructions(entity.getInstructions());

        if (entity.getImage() != null) {
            try {
                result.setImage(new URI(entity.getImage()));
            } catch (URISyntaxException e) {
                LOGGER.error(e.getMessage());
            }
        }

        if (entity.getIngredients() != null) {
            result.setIngredients(entity.getIngredients());
        }

        return result;
    }
}
