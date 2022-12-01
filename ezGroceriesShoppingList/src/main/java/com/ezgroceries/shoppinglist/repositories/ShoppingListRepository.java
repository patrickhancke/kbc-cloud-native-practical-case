package com.ezgroceries.shoppinglist.repositories;

import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.entities.CocktailShoppingListEntity;
import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface ShoppingListRepository extends Repository<ShoppingListEntity,Long> {

    public ShoppingListEntity save(ShoppingListEntity shoppingList);
    public ShoppingListEntity findByid(UUID CocktailListId);

}
