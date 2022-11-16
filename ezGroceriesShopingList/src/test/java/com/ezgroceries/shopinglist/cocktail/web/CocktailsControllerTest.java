package com.ezgroceries.shopinglist.cocktail.web;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shopinglist.cocktail.CocktailDBClient;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailRepository;
import com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListsService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    private ShoppingListsService shoppingListsService;

    @MockBean
    private CocktailDBClient cocktailDBClient;

    @MockBean
    private CocktailRepository cocktailRepository;

    @BeforeEach
    public void setUp() {
        given(cocktailDBClient.searchCocktails(any())).willReturn(getCocktails());
        given(cocktailRepository.saveAll(any())).willReturn(getCreatedEntities());
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
                .andExpect(jsonPath("$.[0].name", equalTo("Margerita")))
                .andExpect(jsonPath("$.[0].ingredients", hasItem("Lime juice")))
                .andExpect(jsonPath("$.[1].name", equalTo("Blue Margerita")));
    }

    private CocktailDBResponse getCocktails() {
        Map<String, DrinkResource> cocktails = new HashMap<>();
        DrinkResource cocktail = new DrinkResource();
        cocktail.setIdDrink("1234");
        cocktail.setStrDrink("Margerita");
        cocktail.setStrGlass("Cocktail glass");
        cocktail.setStrInstructions("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..");
            cocktail.setStrDrinkThumb("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg");

        cocktail.setStrIngredient1("Tequila");
        cocktail.setStrIngredient2("Triple sec");
        cocktail.setStrIngredient3("Lime juice");
        cocktails.put(cocktail.getIdDrink(), cocktail);

        cocktail = new DrinkResource();
        cocktail.setIdDrink("5678");
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

    private Collection<CocktailEntity> getCreatedEntities() {
        return getCocktails().getDrinks().stream().map(this::create).collect(Collectors.toList());
    }

    private CocktailEntity create (DrinkResource resource) {
        CocktailEntity cocktailEntity = new CocktailEntity();

        cocktailEntity.setId(UUID.randomUUID());
        cocktailEntity.setIdDrink(resource.getIdDrink());
        cocktailEntity.setIngredients(Stream.of(resource.getStrIngredient1(), resource.getStrIngredient2(), resource.getStrIngredient3())
                .filter(Objects::nonNull)
                .collect(Collectors.toSet()));
        cocktailEntity.setName(resource.getStrDrink());

        return cocktailEntity;
    }

}
