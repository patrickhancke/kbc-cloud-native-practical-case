package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.Classes.ShoppingList;
import com.ezgroceries.shoppinglist.Controllers.ShoppingListController;
import com.ezgroceries.shoppinglist.Managers.CocktailManager;
import com.ezgroceries.shoppinglist.Managers.ShoppingListManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
//@WebMvcTest(ShoppingListController.class)

public class MockMvcTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockMvc mockMvc;

@MockBean
private ShoppingListManager shoppingListManager;

    @Test
    public void getCocktailsTest() throws Exception {
        int expectedNumberOfAccounts = 2;

       this.mockMvc //
                .perform(get("/cocktails") //
                        .accept(MediaType.parseMediaType("application/json"))) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.length()").value(expectedNumberOfAccounts));

    }


    @Test
    public void getAccountTest() throws Exception {
        // arrange
       given(shoppingListManager.getShoppingList("290689338-499a-4c49-af90-f1e73068ad4f"))
               .willReturn(Collections.singletonList(new ShoppingList("290689338-499a-4c49-af90-f1e73068ad4f",
                       "Stephanie's birthday", Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt,Blue Curacao"))));

        // act and assert
        mockMvc.perform(get("/shopping-lists/290689338-499a-4c49-af90-f1e73068ad4f"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1));


        // verify
      verify(shoppingListManager).getShoppingList("290689338-499a-4c49-af90-f1e73068ad4f");
    }

    @Test
    public void addShoppingList() throws Exception {

        mockMvc.perform(post("/shopping-lists/{shoppingListId}/cocktails", 1).content("Test"))
                .andExpect(status().isCreated());
    }

}
