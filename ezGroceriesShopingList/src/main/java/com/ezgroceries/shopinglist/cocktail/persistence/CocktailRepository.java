package com.ezgroceries.shopinglist.cocktail.persistence;

import java.util.Collection;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CocktailRepository extends CrudRepository<CocktailEntity, UUID> {

    CocktailEntity findByName(String name);
    Collection<CocktailEntity> findByIdDrinkIn(Collection<String> drunkIds);

    Collection<CocktailEntity> findByNameContainingIgnoreCase(String searchTerm);

}
