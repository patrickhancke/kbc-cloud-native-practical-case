package com.ezgroceries.shopinglist.cocktail.web;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.service.CocktailsService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class CocktailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailsService cocktailsService;

    @BeforeEach
    private void setUp() {
        given(cocktailsService.getCocktails()).willReturn(getCocktails());    }

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
                .andExpect(jsonPath("$.[0].name", equalTo("Margerita")))
                .andExpect(jsonPath("$.[0].glass", equalTo("Cocktail glass")))
                .andExpect(jsonPath("$.[0].image", equalTo("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg")))
                .andExpect(jsonPath("$.[1].name", equalTo("Blue Margerita")));
    }

    private List<Cocktail> getCocktails() {
        Map<UUID, Cocktail> cocktails = new HashMap<>();
        Cocktail cocktail = new Cocktail();
        cocktail.setCocktailId(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"));
        cocktail.setName("Margerita");
        cocktail.setGlass("Cocktail glass");
        cocktail.setInstructions("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..");
        try {
            cocktail.setImage(new URI("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        cocktail.setIngredients(List.of("Tequila",
                "Triple sec",
                "Lime juice",
                "Salt"));
        cocktails.put(cocktail.getCocktailId(), cocktail);

        cocktail = new Cocktail();
        cocktail.setCocktailId(UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"));
        cocktail.setName("Blue Margerita");
        cocktail.setGlass("Cocktail glass");
        cocktail.setInstructions("Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..");
        try {
            cocktail.setImage(new URI("https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        cocktail.setIngredients(List.of("Tequila",
                "Blue Curacao",
                "Lime juice",
                "Salt"));

        cocktails.put(cocktail.getCocktailId(), cocktail);
        return new ArrayList<>(cocktails.values());
    }

}
