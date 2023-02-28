package com.ezgroceries.shoppinglist.cocktail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Ingredient {
    private String name;
}
