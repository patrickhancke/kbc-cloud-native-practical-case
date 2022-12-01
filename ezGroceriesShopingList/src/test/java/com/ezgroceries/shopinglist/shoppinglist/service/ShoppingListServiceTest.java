package com.ezgroceries.shopinglist.shoppinglist.service;

import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_DRINK_ID;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_GLASS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_IMAGE;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_INGREDIENT;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_INSTRUCTIONS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_NAME;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_1_UUID;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_GLASS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_IMAGE;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_INGREDIENT;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_INGREDIENT2;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_INSTRUCTIONS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_NAME;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_2_UUID;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_GLASS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_IMAGE;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_INGREDIENT;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_INGREDIENT2;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_INSTRUCTIONS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_NAME;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_UUID;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_AREA;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_AREA_1;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_CATEGORY;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_CATEGORY_1;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_ID;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_ID_1;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_ID_MEAL;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_ID_MEAL_1;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_IMAGE;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_IMAGE_1;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_INGREDIENT;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_INGREDIENT_1;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_INSTRUCTIONS;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_INSTRUCTIONS_1;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_NAME;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.MEAL_NAME_1;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.SHOPPING_LIST_NAME;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.SHOPPING_LIST_UUID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

import com.ezgroceries.shopinglist.cocktail.AddCocktailListener;
import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.Cocktail1Matcher;
import com.ezgroceries.shopinglist.cocktail.Cocktail2Matcher;
import com.ezgroceries.shopinglist.cocktail.Cocktail3Matcher;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailRepository;
import com.ezgroceries.shopinglist.cocktail.service.CocktailService;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesBadRequestException;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesNotFoundException;
import com.ezgroceries.shopinglist.meal.persistence.MealEntity;
import com.ezgroceries.shopinglist.meal.persistence.MealRepository;
import com.ezgroceries.shopinglist.meal.service.MealMatcher;
import com.ezgroceries.shopinglist.meal.web.Meal;
import com.ezgroceries.shopinglist.shoppinglist.ShoppingList;
import com.ezgroceries.shopinglist.shoppinglist.persistence.ShoppingListEntity;
import com.ezgroceries.shopinglist.shoppinglist.persistence.ShoppingListRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

@DataJpaTest
public class ShoppingListServiceTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @MockBean
    private CocktailService cocktailService;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private CocktailRepository cocktailRepository;
    @Autowired
    private ShoppingListRepository shoppingListRepository;
    private ShoppingListsService shoppingListsService;

    @BeforeEach
    void setup() {
        init();
        shoppingListsService = new ShoppingListsService(shoppingListRepository, cocktailService, mealRepository);
        given(cocktailService.getCocktailEntity(COCKTAIL_3_UUID)).willReturn(createCocktail3());
        given(cocktailService.getCocktailEntity(SHOPPING_LIST_UUID)).willThrow(new EzGroceriesNotFoundException("error"));
    }

    @Test
    @WithMockUser(username = "Eminem")
    void testGetShoppingList() {
        ShoppingList shoppingList = shoppingListsService.getShoppingList(SHOPPING_LIST_UUID);
        assertNotNull(shoppingList);
        assertEquals(SHOPPING_LIST_UUID, shoppingList.getShoppingListId());
        assertEquals(SHOPPING_LIST_NAME, shoppingList.getName());
        assertNotNull(shoppingList.getCocktails());
        assertEquals(2, shoppingList.getCocktails().size());
        shoppingList.getCocktails().sort(Comparator.comparing(Cocktail::getName));
        assertThat(shoppingList.getCocktails(), hasItems(new Cocktail2Matcher()));
        assertThat(shoppingList.getMeals(), hasItems(new MealMatcher()));
    }

    @Test
    @WithMockUser(username = "John")
    void testGetShoppingListOfAnotherUser() {
        Assertions.assertThrows(EzGroceriesNotFoundException.class, () ->
                shoppingListsService.getShoppingList(SHOPPING_LIST_UUID));
    }

    @Test
    void testGetNotFound() {
        Assertions.assertThrows(EzGroceriesNotFoundException.class, () ->
                shoppingListsService.getShoppingList(COCKTAIL_2_UUID));
    }

    @Test
    void testGetNull() {
        Assertions.assertThrows(EzGroceriesBadRequestException.class, () ->
                shoppingListsService.getShoppingList(null));
    }

    @Test
    @WithMockUser(username = "Eminem")
    void testAddCocktailToShoppingList() {
        shoppingListsService.addCocktailToShoppingList(SHOPPING_LIST_UUID, COCKTAIL_3_UUID);

        ShoppingList shoppingList = shoppingListsService.getShoppingList(SHOPPING_LIST_UUID);
        assertThat(shoppingList.getCocktails().size(), equalTo(3));
        List<Cocktail> cocktails = shoppingList.getCocktails();
        cocktails.sort(Comparator.comparing(Cocktail::getName));
        assertThat(cocktails, containsInAnyOrder(new Cocktail2Matcher(), new Cocktail3Matcher(), new Cocktail1Matcher()));
    }

    @Test
    @WithMockUser(username = "Eminem")
    void testAddMealToShoppingList() {
        shoppingListsService.addMealToShoppingList(SHOPPING_LIST_UUID, MEAL_ID_1);

        ShoppingList shoppingList = shoppingListsService.getShoppingList(SHOPPING_LIST_UUID);
        assertThat(shoppingList.getMeals().size(), equalTo(2));
        List<Meal> meals = shoppingList.getMeals();
        meals.sort(Comparator.comparing(Meal::getName));
        assertThat(meals, hasItem(new MealMatcher()));
    }

    @Test
    void testCocktailListener() {
        AddCocktailListener addCocktailListener = new AddCocktailListener(cocktailRepository);
        CocktailEntity cocktail3 = createCocktail3();
        addCocktailListener.foundMissingCocktails(cocktail3);

        CocktailEntity byName = cocktailRepository.findByName(cocktail3.getName());

        assertThat(byName.getIdDrink(), equalTo(cocktail3.getIdDrink()));
        assertThat(byName.getId(), equalTo(cocktail3.getId()));
        assertThat(byName.getGlass(), equalTo(cocktail3.getGlass()));
        assertThat(byName.getImage(), equalTo(cocktail3.getImage()));
        assertThat(byName.getInstructions(), equalTo(cocktail3.getInstructions()));
        assertThat(byName.getIngredients(), containsInAnyOrder(COCKTAIL_3_INGREDIENT, COCKTAIL_3_INGREDIENT2));
    }

    @Test
    void testAddInvalidCocktailToShoppingList() {
        Assertions.assertThrows(EzGroceriesNotFoundException.class, () -> shoppingListsService.addCocktailToShoppingList(SHOPPING_LIST_UUID, SHOPPING_LIST_UUID));
    }

    @Test
    void testAddCocktailToInvalidShoppingList() {
        Assertions.assertThrows(EzGroceriesNotFoundException.class, () -> shoppingListsService.addCocktailToShoppingList(COCKTAIL_3_UUID, COCKTAIL_3_UUID));
    }

    @Test
    void testAddNullToShoppingList() {
        Assertions.assertThrows(EzGroceriesBadRequestException.class, () -> shoppingListsService.addCocktailToShoppingList(null, COCKTAIL_3_UUID));
    }

    @Test
    void testAddCocktailToNullShoppingList() {
        Assertions.assertThrows(EzGroceriesBadRequestException.class, () -> shoppingListsService.addCocktailToShoppingList(SHOPPING_LIST_UUID, null));
    }

    @Test
    @WithMockUser(username = "Eminem")
    void testCreateShoppingList() {
        ShoppingList newlyCreated = shoppingListsService.createShoppingList(SHOPPING_LIST_NAME);
        assertThat(newlyCreated.getName(), equalTo(SHOPPING_LIST_NAME));

    }

    @Test
    @WithMockUser(username = "Eminem")
    void testCreateInvalidShoppingList() {
        ShoppingList newlyCreated = shoppingListsService.createShoppingList(null);
        assertThat(newlyCreated.getName(), equalTo(null));
    }

    private void init() {
        testEntityManager.persist(createShoppingList());
        testEntityManager.persist(createCocktail());
        testEntityManager.persist(createCocktail2());
        testEntityManager.persist(createCocktail3());
        testEntityManager.persist(createMeal());
        testEntityManager.persist(createMeal1());
        testEntityManager.flush();
    }

    private ShoppingListEntity createShoppingList() {
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity();

        shoppingListEntity.setId(SHOPPING_LIST_UUID);
        shoppingListEntity.setName(SHOPPING_LIST_NAME);
        shoppingListEntity.setUsername("Eminem");
        shoppingListEntity.setCocktails(Stream.of(createCocktail(), createCocktail2()).collect(Collectors.toSet()));
        shoppingListEntity.setMeals(Stream.of(createMeal()).collect(Collectors.toSet()));

        return shoppingListEntity;
    }

    private CocktailEntity createCocktail() {
        CocktailEntity cocktailEntity = new CocktailEntity();
        cocktailEntity.setId(COCKTAIL_1_UUID);
        cocktailEntity.setIdDrink(COCKTAIL_1_DRINK_ID);
        cocktailEntity.setName(COCKTAIL_1_NAME);
        cocktailEntity.setGlass(COCKTAIL_1_GLASS);
        cocktailEntity.setImage(COCKTAIL_1_IMAGE);
        cocktailEntity.setIngredients(Set.of(COCKTAIL_1_INGREDIENT, "ingredient 2 here"));
        cocktailEntity.setInstructions(COCKTAIL_1_INSTRUCTIONS);

        return cocktailEntity;
    }

    private CocktailEntity createCocktail2() {
        CocktailEntity cocktailEntity = new CocktailEntity();
        cocktailEntity.setId(COCKTAIL_2_UUID);
        cocktailEntity.setIdDrink("2345");
        cocktailEntity.setName(COCKTAIL_2_NAME);
        cocktailEntity.setGlass(COCKTAIL_2_GLASS);
        cocktailEntity.setImage(COCKTAIL_2_IMAGE);
        cocktailEntity.setIngredients(Set.of(COCKTAIL_2_INGREDIENT, COCKTAIL_2_INGREDIENT2));
        cocktailEntity.setInstructions(COCKTAIL_2_INSTRUCTIONS);

        return cocktailEntity;
    }

    private CocktailEntity createCocktail3() {
        CocktailEntity cocktailEntity = new CocktailEntity();
        cocktailEntity.setId(COCKTAIL_3_UUID);
        cocktailEntity.setIdDrink("3456");
        cocktailEntity.setName(COCKTAIL_3_NAME);
        cocktailEntity.setGlass(COCKTAIL_3_GLASS);
        cocktailEntity.setImage(COCKTAIL_3_IMAGE);
        cocktailEntity.setIngredients(Set.of(COCKTAIL_3_INGREDIENT, COCKTAIL_3_INGREDIENT2));
        cocktailEntity.setInstructions(COCKTAIL_3_INSTRUCTIONS);

        return cocktailEntity;
    }

    private MealEntity createMeal() {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setId(MEAL_ID);
        mealEntity.setIdMeal(MEAL_ID_MEAL);
        mealEntity.setName(MEAL_NAME);
        mealEntity.setImage(MEAL_IMAGE);
        mealEntity.setInstructions(MEAL_INSTRUCTIONS);
        mealEntity.setCategory(MEAL_CATEGORY);
        mealEntity.setArea(MEAL_AREA);
        mealEntity.setIngredients(Set.of(MEAL_INGREDIENT));
        return mealEntity;
    }

    private MealEntity createMeal1() {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setId(MEAL_ID_1);
        mealEntity.setIdMeal(MEAL_ID_MEAL_1);
        mealEntity.setName(MEAL_NAME_1);
        mealEntity.setImage(MEAL_IMAGE_1);
        mealEntity.setInstructions(MEAL_INSTRUCTIONS_1);
        mealEntity.setCategory(MEAL_CATEGORY_1);
        mealEntity.setArea(MEAL_AREA_1);
        mealEntity.setIngredients(Set.of(MEAL_INGREDIENT_1));
        return mealEntity;
    }
}
