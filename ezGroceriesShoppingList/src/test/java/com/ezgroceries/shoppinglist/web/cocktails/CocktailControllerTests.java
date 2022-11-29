package com.ezgroceries.shoppinglist.web.cocktails;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@AutoConfigureMockMvc
@WebMvcTest(CocktailController.class)
//@AutoConfigureMockMvc
public class CocktailControllerTests {

    @Autowired
    private MockMvc mockMvc;

    //Old test dummies, copied from stub
    private static final Set<String> stubIngredients = new HashSet<>(Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt"));

    private static final Cocktail margerita = new Cocktail("23b3d85a-3928-41c0-a533-6538a71e17c4", "Margerita",
            "Cocktail glass", "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
            "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
            stubIngredients);

    //New test dummies:
    private static final CocktailDBResponse margeritaDbResp = new CocktailDBResponse();
    private static final CocktailDBResponse.DrinkResource drinkResource = new CocktailDBResponse.DrinkResource();
    static {
        drinkResource.setIdDrink("testId");
        drinkResource.setStrDrink("Margerita");
        drinkResource.setStrGlass("Cocktail glass");
        margeritaDbResp.setDrinks(List.of(drinkResource));
    }

    @MockBean
    private CocktailService cocktailService; //old mockbean
    //@MockBean CocktailDBClient cocktailDBClient;


    @Test
    public void getAllCocktails() throws Exception{

        given(cocktailService.searchCocktail("Russian")).willReturn(List.of(margerita));
        //given(cocktailDBClient.searchCocktails("Russian")).willReturn(margeritaDbResp);

        mockMvc.perform(get("/cocktails").param("search","Russian"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].glass").value("Cocktail glass"))
                .andExpect(jsonPath("$[0].name").value("Margerita"));

        verify(cocktailService).searchCocktail("Russian");
        //verify(cocktailDBClient).searchCocktails("Russian");
    }


}
