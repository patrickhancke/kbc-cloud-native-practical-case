package com.ezgroceries.shoppinglist.web.shoppinglists;

import com.ezgroceries.shoppinglist.web.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.web.cocktails.CocktailRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ShoppingListServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ShoppingListServiceTest.class);
    private final ShoppingList stephanieDummyShoppingList = new ShoppingList(UUID.randomUUID(),
            "Stephanie's birthday",
            new String[]{"Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"});

    private final ShoppingList myDummyShoppingList = new ShoppingList(UUID.randomUUID(),
            "My birthday",
            new String[]{"Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"});




    @MockBean
    ShoppingListRepository shoppingListRepository;

    @Autowired
    ShoppingListService shoppingListService;

    /*
    public ShoppingListServiceTest(ShoppingListService shoppingListService, ShoppingListRepository shoppingListRepository){
        this.shoppingListService = shoppingListService;
        this.shoppingListRepository = shoppingListRepository;
    }
     */

    @Test
    public void testService(){
        Mockito.when(shoppingListRepository.findAll()).thenReturn(new ArrayList<>());
        assert(shoppingListService.getAllShoppingLists().size()==0);

        Mockito.when(shoppingListRepository.findAll()).thenReturn(List.of(myDummyShoppingList,stephanieDummyShoppingList));
        assert(shoppingListService.getAllShoppingLists().size()==2);

        UUID testId = UUID.randomUUID();
        Mockito.when(shoppingListRepository.findById(testId)).thenReturn(Optional.empty());
        assertTrue(shoppingListService.getShoppingList(testId).equals(Optional.empty()));
        assertFalse(shoppingListService.getShoppingList(testId).equals(null));

        Mockito.when(shoppingListRepository.findById(testId)).thenReturn(Optional.of(myDummyShoppingList));
        assert(shoppingListService.getShoppingList(testId).equals(Optional.of(myDummyShoppingList)));

        /*
        UUID testId2 = UUID.randomUUID();
        //myDummyShoppingList.setId(testId2);
        Mockito.when(shoppingListRepository.save(myDummyShoppingList)).thenAnswer(invocation -> {
            myDummyShoppingList.setId(testId2);
            return testId2;
        });
        log.info("shopping list created {}", shoppingListService.createShoppingList(myDummyShoppingList.getName()));
        //assert(testId2.equals(shoppingListService.createShoppingList(myDummyShoppingList.getName())));
        */


    }
}
