package com.ezgroceries.shoppinglist.repositories;

import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.entities.CocktailResource;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CocktailRepository extends Repository<CocktailEntity,Long> {

    public List<CocktailEntity> findByIdDrinkIn(List<String> idDrink);
    public List<CocktailEntity> findByIdIn(List<UUID> id);
    public CocktailEntity save(CocktailEntity cocktail);
}