package com.ezgroceries.shoppinglist.cocktail;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Cocktail {
    private UUID cocktailId;
    private String name;
    private String glass;
    private String instructions;
    private String image;
    private List<Ingredient> ingredients;

}
