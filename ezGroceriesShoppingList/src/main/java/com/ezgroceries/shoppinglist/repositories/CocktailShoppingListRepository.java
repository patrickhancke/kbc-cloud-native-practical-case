package com.ezgroceries.shoppinglist.repositories;

import com.ezgroceries.shoppinglist.entities.CocktailShoppingListEntity;
import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface CocktailShoppingListRepository extends Repository<CocktailShoppingListEntity,Long> {
    public CocktailShoppingListEntity save(CocktailShoppingListEntity cocktailShoppingList);
    public CocktailShoppingListEntity findByid(UUID CocktailListId);
    public List<CocktailShoppingListEntity> findByshoppingListId(UUID ShoppingListId);
}
