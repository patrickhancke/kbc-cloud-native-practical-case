package com.ezgroceries.shoppinglist.Cocktail;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CocktailDTO {
    private UUID cocktailId;
    private String name;
    private String glass;
    private String instructions;
    private String image;
    private List<String> ingredients;

}
