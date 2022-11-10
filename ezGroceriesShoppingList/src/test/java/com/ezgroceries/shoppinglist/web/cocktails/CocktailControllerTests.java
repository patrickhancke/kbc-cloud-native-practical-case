package com.ezgroceries.shoppinglist.web.cocktails;

import com.ezgroceries.shoppinglist.web.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.web.cocktails.CocktailController;
import com.ezgroceries.shoppinglist.web.cocktails.CocktailManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

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

    //copy from stub
    private Cocktail margerita = new Cocktail("23b3d85a-3928-41c0-a533-6538a71e17c4", "Margerita",
            "Cocktail glass", "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
            "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
            new String[] { "Tequila", "Triple sec", "Lime juice", "Salt"});

    @MockBean
    private CocktailManager cocktailManager;


    @Test
    public void getAllCocktails() throws Exception{
        given(cocktailManager.searchCocktail("Russian"))
                .willReturn(List.of(margerita));


        mockMvc.perform(get("/cocktails").param("search","Russian"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].glass").value("Cocktail glass"))
                .andExpect(jsonPath("$[0].name").value("Margerita"));

        verify(cocktailManager).searchCocktail("Russian");

    }


}
