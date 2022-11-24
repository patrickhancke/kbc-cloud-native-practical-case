package com.ezgroceries.shoppinglist.web.shoppinglists;

import com.ezgroceries.shoppinglist.web.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.web.cocktails.CocktailManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@Import(AppConfig.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebMvcTest
//@AutoConfigureMockMvc
public class ShoppingControllerTests {
    private static final String URLPREFIX = "http://localhost";

    private static final Logger log = LoggerFactory.getLogger(ShoppingControllerTests.class);

    private final ShoppingList stephanieDummyShoppingList = new ShoppingList("90689338-499a-4c49-af90-f1e73068ad4f",
            "Stephanie's birthday",
            new String[]{"Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"});

    private final ShoppingList myDummyShoppingList = new ShoppingList("6c7d09c2-8a25-4d54-a979-25ae779d2465",
            "My birthday"
            , new String[]{"Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"}
    );

    private final Cocktail margerita = new Cocktail("23b3d85a-3928-41c0-a533-6538a71e17c4", "Margerita",
            "Cocktail glass", "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
            "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
            new String[] { "Tequila", "Triple sec", "Lime juice", "Salt"});

    private final String dummyListId = "testId";


    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ShoppingListManager shoppingListManager;

    @MockBean
    private CocktailManager cocktailManager; //this to get the tests to work, fails on CocktailManager init
    // TODO other option is to use profiles so the feignClient is not used here?

    @Test
    public void testBasicCall() throws Exception{
        mockMvc.perform(get("/shopping-lists"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllLists() throws Exception{
        given(shoppingListManager.getAllShoppingLists())
                .willReturn(List.of(stephanieDummyShoppingList));


        mockMvc.perform(get("/shopping-lists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Stephanie's birthday"));

        verify(shoppingListManager).getAllShoppingLists();
    }

    @Test
    public void createList() throws Exception{
        //given(shoppingListManager.createShoppingList("My birthday"))
        //        .willReturn(null);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(myDummyShoppingList);
        log.info("json made: {}", json);

        mockMvc.perform(post("/shopping-lists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void getList() throws Exception {
        //get non-existing
        mockMvc.perform(get("/shopping-lists/"+dummyListId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();

        //look for existing
        given(shoppingListManager.getShoppingList(dummyListId))
                .willReturn(stephanieDummyShoppingList);

        mockMvc.perform(get("/shopping-lists/"+dummyListId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Stephanie's birthday"))
                .andReturn();
    }

    @Test
    void addCocktailToList() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(margerita);
        log.info("json made: {}", json);

        mockMvc.perform(post("/shopping-lists/"+dummyListId+"/cocktails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", URLPREFIX + "/shopping-lists/"+dummyListId+"/cocktails/"+margerita.getCocktailId()))
                .andReturn();
    }

}

