package com.ezgroceries.shopinglist.shoppinglist.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shopinglist.cocktail.service.CocktailService;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesBadRequestException;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesNotFoundException;
import com.ezgroceries.shopinglist.shoppinglist.ShoppingList;
import com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
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
public class ShoppingListControllerTest {

    private static final UUID SHOPPING_LIST_ID = UUID.fromString("69dda986-3dd0-4466-a519-a972723dcd71");
    private static final String LIST_NAME = "Random Name";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingListsService shoppingListsService;

    @MockBean
    private CocktailService cocktailService;

    @BeforeEach
    void setUp() {
        given(shoppingListsService.getShoppingList(UUID.fromString("69dda986-3dd0-4466-a519-a972723dcd71")))
                .willReturn(createShoppingList());
        given(shoppingListsService.getShoppingList(UUID.fromString("69dda986-1dd0-4466-a519-a972723dcd71")))
                .willThrow(EzGroceriesBadRequestException.class);
        given(shoppingListsService.addCocktailToShoppingList(UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073")))
                .willThrow(EzGroceriesNotFoundException.class);
        given(shoppingListsService.addCocktailToShoppingList(UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), UUID.fromString("d615ec68-fe93-467b-8d26-5d26d8eab073")))
                .willThrow(EzGroceriesBadRequestException.class);
        given(shoppingListsService.createShoppingList(any())).willReturn(createShoppingList());
    }

    @Test
    public void testGetShoppingList() throws Exception {
        mockMvc.perform(get("/shopping-lists/69dda986-3dd0-4466-a519-a972723dcd71"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value(LIST_NAME))
                .andExpect(jsonPath("shoppingListId").value(SHOPPING_LIST_ID.toString()));
    }

    @Test
    public void testInvalidIdGetShoppingList() throws Exception {
        mockMvc.perform(get("/shopping-lists/69dda986-1dd0-4466-a519-a972723dcd71"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateShoppingList() throws Exception {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName("Stephanie's birthday");
        mockMvc.perform(post("/shopping-lists").contentType(MediaType.APPLICATION_JSON).content(asJsonString(shoppingList)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/shopping-lists/69dda986-3dd0-4466-a519-a972723dcd71"));
    }

    @Test
    public void testInvalidNameCreateShoppingList() throws Exception {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName("Vvvvvvvvvvveeeeeeerrrrrrrrrrryyyyyyyyyyy llllllllllloooooooooonnnnnnnnggggggg nnnnnnnnnnaaaaaaaaammmmmmmmmeeeeeeeee");
        mockMvc.perform(post("/shopping-lists").contentType(MediaType.APPLICATION_JSON).content(asJsonString(shoppingList)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddCocktailToShoppingList() throws Exception {
        mockMvc.perform(post("/shopping-lists/69dda986-3dd0-4466-a519-a972723dcd71/cocktails?cocktailId=d615ec78-fe93-467b-8d26-5d26d8eab073"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/shopping-lists/69dda986-3dd0-4466-a519-a972723dcd71/cocktails/d615ec78-fe93-467b-8d26-5d26d8eab073"));
    }

    @Test
    public void testAddCocktailInvalidShoppingList() throws Exception {
        mockMvc.perform(post("/shopping-lists/d615ec78-fe93-467b-8d26-5d26d8eab073/cocktails?cocktailId=d615ec68-fe93-467b-8d26-5d26d8eab073"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testMissingShoppingList() throws Exception {
        mockMvc.perform(post("/shopping-lists/d615ec78-fe93-467b-8d26-5d26d8eab073/cocktails?cocktailId=d615ec78-fe93-467b-8d26-5d26d8eab073"))
                .andExpect(status().isNotFound());
    }

    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ShoppingList createShoppingList() {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setShoppingListId(SHOPPING_LIST_ID);
        shoppingList.setName(LIST_NAME);

        return shoppingList;
    }
}
