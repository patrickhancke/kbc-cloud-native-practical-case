package com.ezgroceries.shopinglist.meal.web;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shopinglist.EzGroceriesShopingListApplication;
import com.ezgroceries.shopinglist.cocktail.service.CocktailService;
import com.ezgroceries.shopinglist.meal.service.MealService;
import com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListsService;
import config.TestConfig;
import java.util.Collection;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@ActiveProfiles("hsqldb")
@ContextConfiguration(classes = {EzGroceriesShopingListApplication.class, TestConfig.class})
public class MealControllerTest {
    private static final String MEAL_NAME = "meal name";
    private static final String MEAL_IMAGE = "meal image";
    private static final String MEAL_AREA = "meal area";
    private static final String MEAL_CATEGORY = "meal category";
    private static final String MEAL_INSTRUCTIONS = "meal instructions";
    private static final String MEAL_INGREDIENT = "meal ingredient";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MealService mealService;
    @MockBean
    private CocktailService cocktailService;
    @MockBean
    private ShoppingListsService shoppingListsService;

    @BeforeEach
    void setup() {
        given(mealService.search(any())).willReturn(getMeals());
        given(mealService.search(null)).willReturn(getMeals());
    }

    @Test
    @WithMockUser
    void testEmptySearchForMeal() throws Exception {
        mockMvc.perform(get("/meals"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo(MEAL_NAME)))
                .andExpect(jsonPath("$[0].image", equalTo(MEAL_IMAGE)))
                .andExpect(jsonPath("$[0].area", equalTo(MEAL_AREA)))
                .andExpect(jsonPath("$[0].category", equalTo(MEAL_CATEGORY)));
    }

    @Test
    void testSearchForMeal() throws Exception {
        mockMvc.perform(get("/meals?searchTerm=test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo(MEAL_NAME)))
                .andExpect(jsonPath("$[0].image", equalTo(MEAL_IMAGE)))
                .andExpect(jsonPath("$[0].area", equalTo(MEAL_AREA)))
                .andExpect(jsonPath("$[0].category", equalTo(MEAL_CATEGORY)));
    }

    private Collection<Meal> getMeals() {
        Meal meal = new Meal();
        meal.setName(MEAL_NAME);
        meal.setImage(MEAL_IMAGE);
        meal.setCategory(MEAL_CATEGORY);
        meal.setArea(MEAL_AREA);
        meal.setInstructions(MEAL_INSTRUCTIONS);
        meal.setIngredients(Set.of(MEAL_INGREDIENT));

        return singletonList(meal);
    }

}
