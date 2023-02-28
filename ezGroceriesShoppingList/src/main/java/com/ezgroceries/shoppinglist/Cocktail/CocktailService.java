package com.ezgroceries.shoppinglist.Cocktail;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CocktailService {

    public List<CocktailDTO> getCocktails(String search) {
        List<CocktailDTO> cocktailDTOList = new ArrayList<>();
        CocktailDTO cocktailDTO1= CocktailDTO.builder()
                .cocktailId(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"))
                .name("Margerita")
                .glass("Cocktail glass")
                .instructions("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..")
                .image("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg")
                .ingredients(Arrays.asList("Tequila",
                        "Triple sec",
                        "Lime juice",
                        "Salt"))
                .build();
        CocktailDTO cocktailDTO2= CocktailDTO.builder()
                .cocktailId(UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"))
                .name("Blue Margerita")
                .glass("Cocktail glass")
                .instructions("Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..")
                .image("https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg")
                .ingredients(Arrays.asList("Tequila",
                        "Blue Curacao",
                        "Lime juice",
                        "Salt"))
                .build();
        cocktailDTOList.add(cocktailDTO1);
        cocktailDTOList.add(cocktailDTO2);
        return cocktailDTOList;
    }
}
