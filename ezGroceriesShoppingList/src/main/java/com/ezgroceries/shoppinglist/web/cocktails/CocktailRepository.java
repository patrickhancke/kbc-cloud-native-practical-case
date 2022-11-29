package com.ezgroceries.shoppinglist.web.cocktails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, UUID> {

    List<Cocktail> findCocktailsByCocktailId(String idDrink);

    List<Cocktail> findByNameContainingIgnoreCase(String search);
}