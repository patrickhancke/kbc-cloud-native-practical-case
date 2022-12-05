package com.ezgroceries.shoppinglist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


@Entity
@Table(name = "COCKTAIL_SHOPPING_LIST")
public class CocktailShoppingListEntity {

    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "SHOPPING_LIST_ID")
    private UUID shoppingListId;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public UUID getShopping_list_id() {
        return shoppingListId;
    }

    public void setShopping_list_id(UUID shopping_list_id) {
        this.shoppingListId = shopping_list_id;
    }
}