package com.ezgroceries.shopinglist.cocktail.web;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.service.CocktailService;
import com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListsService;
import java.util.Collection;
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
        CocktailControllerTestConfiguration.class,
        CocktailController.class})
public class CocktailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Collection<Cocktail> cocktails;

    @MockBean
    private ShoppingListsService shoppingListsService;

    @MockBean
    private CocktailService cocktailService;

    @BeforeEach
    public void setUp() {
        given(cocktailService.searchByTerm(any())).willReturn(cocktails);
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
}
