package com.ezgroceries.shopinglist.cocktail.web;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shopinglist.EzGroceriesShopingListApplication;
import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.service.CocktailService;
import com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListsService;
import config.TestConfig;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@ActiveProfiles("hsqldb")
@ContextConfiguration(classes = {
        EzGroceriesShopingListApplication.class,
        TestConfig.class,
        CocktailController.class})
public class CocktailControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailService cocktailService;
    @MockBean
    private ShoppingListsService shoppingListsService;
    @MockBean
    private Cocktail cocktail;

    @BeforeEach
    public void setUp() {
        given(cocktailService.searchByTerm(any())).willReturn(cocktails());
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
        mockMvc.perform(get("/cocktails?searchTerm=test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", equalTo(2)))
                .andExpect(jsonPath("$.[1].name", equalTo("Margerita")))
                .andExpect(jsonPath("$.[1].ingredients", hasItem("Lime juice")))
                .andExpect(jsonPath("$.[0].name", equalTo("Blue Margerita")));
    }

    public Collection<Cocktail> cocktails() {
        var cocktail = new Cocktail();
        cocktail.setName("Margerita");
        cocktail.setGlass("Cocktail glass");
        cocktail.setInstructions("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..");
        cocktail.setIngredients(Set.of("Tequila", "Triple sec", "Lime juice"));
        try {
            cocktail.setImage(new URI("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        var cocktail1 = new Cocktail();
        cocktail1.setName("Blue Margerita");
        cocktail1.setGlass("Cocktail glass");
        cocktail1.setInstructions("Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..");
        cocktail1.setIngredients(Set.of("Tequila", "Blue Curacao", "Lime juice"));
        try {
            cocktail1.setImage(new URI("https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        List<Cocktail> cocktails = Arrays.asList(cocktail, cocktail1);
        cocktails.sort(Comparator.comparing(Cocktail::getName));

        return cocktails;
    }
}
