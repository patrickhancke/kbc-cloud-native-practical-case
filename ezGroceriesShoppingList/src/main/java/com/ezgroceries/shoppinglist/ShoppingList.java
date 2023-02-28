package com.ezgroceries.shoppinglist;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ShoppingList {
    private UUID shoppingListId;
    private String name;
}
