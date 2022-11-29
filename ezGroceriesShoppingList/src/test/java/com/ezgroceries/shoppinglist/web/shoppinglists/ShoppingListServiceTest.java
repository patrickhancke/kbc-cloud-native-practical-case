package com.ezgroceries.shoppinglist.web.shoppinglists;

import com.ezgroceries.shoppinglist.web.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.web.cocktails.CocktailRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.verification.VerificationMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ShoppingListServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ShoppingListServiceTest.class);
    private final ShoppingList stephanieDummyShoppingList = new ShoppingList(UUID.randomUUID(),
            "Stephanie's birthday",
            new String[]{"Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"});

    private final ShoppingList myDummyShoppingList = new ShoppingList(UUID.randomUUID(),
            "My birthday",
            new String[]{"Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"});

    private static final Set<String> stubIngredients = new HashSet<>(Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt"));

    private static final Cocktail margerita = new Cocktail("23b3d85a-3928-41c0-a533-6538a71e17c4", "Margerita",
            "Cocktail glass", "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
            "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
            stubIngredients);



    @MockBean
    ShoppingListRepository shoppingListRepository;
    @MockBean
    CocktailRepository cocktailRepository;

    @Autowired
    ShoppingListService shoppingListService;

    /*
    public ShoppingListServiceTest(ShoppingListService shoppingListService, ShoppingListRepository shoppingListRepository){
        this.shoppingListService = shoppingListService;
        this.shoppingListRepository = shoppingListRepository;
    }
     */

    @Test
    public void testService() {
        //Find all when none available
        Mockito.when(shoppingListRepository.findAll()).thenReturn(new ArrayList<>());
        assert (shoppingListService.getAllShoppingLists().size() == 0);

        //Find all when 2
        Mockito.when(shoppingListRepository.findAll()).thenReturn(List.of(myDummyShoppingList, stephanieDummyShoppingList));
        assert (shoppingListService.getAllShoppingLists().size() == 2);

        //Find by ID
        UUID testId = UUID.randomUUID();
        Mockito.when(shoppingListRepository.findById(testId)).thenReturn(Optional.empty());
        assertTrue(shoppingListService.getShoppingList(testId).equals(Optional.empty()));
        assertFalse(shoppingListService.getShoppingList(testId).equals(null));

        Mockito.when(shoppingListRepository.findById(testId)).thenReturn(Optional.of(myDummyShoppingList));
        assert (shoppingListService.getShoppingList(testId).equals(Optional.of(myDummyShoppingList)));

    }
    @Test
    public void testAddCocktail(){
        //Add cocktail to list
        UUID listId = UUID.randomUUID();
        UUID listId2 = UUID.randomUUID();
        UUID cocktailId = UUID.randomUUID();
        myDummyShoppingList.getCocktails();
        Mockito.when(shoppingListRepository.findById(listId)).thenReturn(Optional.of(myDummyShoppingList));
        Mockito.when(cocktailRepository.findById(cocktailId)).thenReturn(Optional.of(margerita));

        Mockito.when(shoppingListRepository.findById(listId2)).thenReturn(Optional.empty());

        boolean success = shoppingListService.addCocktailToShoppingList(listId, cocktailId); //will be OK
        boolean success2 = shoppingListService.addCocktailToShoppingList(listId2, cocktailId);//will fail

        assertTrue(success);
        assertFalse(success2);
        verify(shoppingListRepository, atMostOnce()).save(myDummyShoppingList);
    }
}
