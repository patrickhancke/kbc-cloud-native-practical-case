package com.ezgroceries.shopinglist.cocktail.persistence;

import java.util.Collection;
import java.util.UUID;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

public interface CocktailRepository extends CrudRepository<CocktailEntity, UUID> {
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
    CocktailEntity findByName(String name);
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
    Collection<CocktailEntity> findByIdDrinkIn(Collection<String> drunkIds);
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
    Collection<CocktailEntity> findByNameContainingIgnoreCase(String searchTerm);

}
