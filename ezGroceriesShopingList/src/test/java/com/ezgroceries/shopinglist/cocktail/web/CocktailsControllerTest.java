package com.ezgroceries.shopinglist.cocktail.web;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shopinglist.cocktail.CocktailDBClient;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse.DrinkResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@ActiveProfiles("hsqldb")
public class CocktailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailDBClient cocktailDBClient;

    @BeforeEach
    private void setUp() {
        given(cocktailDBClient.searchCocktails(any())).willReturn(getCocktails());
    }

    @Test
    public void testGetCocktails() throws Exception {
        mockMvc.perform(get("/cocktails"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", equalTo(2)));
    }

    @Test
    public void testGetCocktailsQueryParam() throws Exception {
        mockMvc.perform(get("/cocktails?searchTerm=\"test\""))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", equalTo(2)))
                .andExpect(jsonPath("$.[1].name", equalTo("Margerita")))
                .andExpect(jsonPath("$.[1].glass", equalTo("Cocktail glass")))
                .andExpect(jsonPath("$.[1].image", equalTo("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg")))
                .andExpect(jsonPath("$.[0].name", equalTo("Blue Margerita")));
    }

    private CocktailDBResponse getCocktails() {
        Map<String, DrinkResource> cocktails = new HashMap<>();
        DrinkResource cocktail = new DrinkResource();
        cocktail.setIdDrink("23b3d85a-3928-41c0-a533-6538a71e17c4");
        cocktail.setStrDrink("Margerita");
        cocktail.setStrGlass("Cocktail glass");
        cocktail.setStrInstructions("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..");
            cocktail.setStrDrinkThumb("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg");

        cocktail.setStrIngredient1("Tequila");
        cocktail.setStrIngredient2("Triple sec");
        cocktail.setStrIngredient3("Lime juice");
        cocktails.put(cocktail.getIdDrink(), cocktail);

        cocktail = new DrinkResource();
        cocktail.setIdDrink("d615ec78-fe93-467b-8d26-5d26d8eab073");
        cocktail.setStrDrink("Blue Margerita");
        cocktail.setStrGlass("Cocktail glass");
        cocktail.setStrInstructions("Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..");
        cocktail.setStrDrinkThumb("https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg");

        cocktail.setStrIngredient1("Tequila");
        cocktail.setStrIngredient2("Blue Curacao");
        cocktail.setStrIngredient3("Lime juice");

        cocktails.put(cocktail.getIdDrink(), cocktail);
        CocktailDBResponse response = new CocktailDBResponse();
        response.setDrinks(new ArrayList<>(cocktails.values()));
        return response;
    }

}
