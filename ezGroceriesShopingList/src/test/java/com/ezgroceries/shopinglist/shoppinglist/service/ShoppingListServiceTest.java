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
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_NAME;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.COCKTAIL_3_UUID;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.SHOPPING_LIST_NAME;
import static com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListServiceTestConfiguration.SHOPPING_LIST_UUID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.Cocktail2Matcher;
import com.ezgroceries.shopinglist.cocktail.Cocktail3Matcher;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shopinglist.cocktail.service.CocktailService;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesBadRequestException;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesNotFoundException;
import com.ezgroceries.shopinglist.shoppinglist.ShoppingList;
import com.ezgroceries.shopinglist.shoppinglist.persistence.ShoppingListEntity;
import com.ezgroceries.shopinglist.shoppinglist.persistence.ShoppingListRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    private ShoppingListRepository shoppingListRepository;
    private ShoppingListsService shoppingListsService;

    @BeforeEach
    void setup() {
        init();
        shoppingListsService = new ShoppingListsService(shoppingListRepository, cocktailService);
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
    @Disabled
    void testAddCocktailToShoppingList() {
        shoppingListsService.addCocktailToShoppingList(SHOPPING_LIST_UUID, COCKTAIL_3_UUID);

        ShoppingList shoppingList = shoppingListsService.getShoppingList(SHOPPING_LIST_UUID);
        assertThat(shoppingList.getCocktails().size(), equalTo(3));
        List<Cocktail> cocktails = shoppingList.getCocktails();
        cocktails.sort(Comparator.comparing(Cocktail::getName));
        assertThat(cocktails, hasItem(new Cocktail3Matcher()));
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
        testEntityManager.flush();
    }

    private ShoppingListEntity createShoppingList() {
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity();

        shoppingListEntity.setId(SHOPPING_LIST_UUID);
        shoppingListEntity.setName(SHOPPING_LIST_NAME);
        shoppingListEntity.setUsername("Eminem");
        shoppingListEntity.setCocktails(Set.of(createCocktail(), createCocktail2()));

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
        cocktailEntity.setInstructions(COCKTAIL_2_INSTRUCTIONS);

        return cocktailEntity;
    }
}
