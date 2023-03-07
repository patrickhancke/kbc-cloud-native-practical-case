package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.cocktail.CocktailDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingListControllerTests {
    private ShoppingListControler controller;
    @BeforeEach
    public void setUp() throws Exception {
        controller = new ShoppingListControler(new ShoppingListService());
    }

    @Test
    public void testCreateShoppingList(){
        setupFakeRequest("http://localhost/shopping-lists");
        ResponseEntity<Void> response = controller.createShoppingList(ShoppingListNameDTO.builder().name("Stephanie's birthday").build());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertEquals("http://localhost/shopping-lists/90689338-499a-4c49-af90-f1e73068ad4f",response.getHeaders().getLocation().toString());
    }

    @Test
    public void addCocktailToShoppingList(){
        UUID shoppingList = UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f");
        setupFakeRequest("http://localhost/shopping-lists/"+shoppingList+"/cocktails");
        ResponseEntity<Void> response = controller.addCocktailIngredientsToList(shoppingList,CocktailDTO.builder().cocktailId(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4")).build());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertEquals("http://localhost/shopping-lists/"+shoppingList+"/cocktails",response.getHeaders().getLocation().toString());
    }
    @Test
    public void getShoppingList(){
        UUID shoppingList = UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f");
        setupFakeRequest("http://localhost/shopping-lists/"+shoppingList);
        ResponseEntity<ShoppingListDTO> response = controller.getShoppingList(shoppingList);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(shoppingList,response.getBody().getShoppingListId());
        assertEquals("Stephanie's birthday",response.getBody().getName());
    }

    @Test
    public void getAllShoppingList(){
        setupFakeRequest("http://localhost/shopping-lists");
        ResponseEntity<List<ShoppingListDTO>> response = controller.getAllShoppingList();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(response.getBody().size(),2);

    }



    /**
     * Add a mocked up HttpServletRequest to Spring's internal request-context
     * holder. Normally the DispatcherServlet does this, but we must do it
     * manually to run our test.
     *
     * @param url
     *            The URL we are creating the fake request for.
     */
    private void setupFakeRequest(String url) {
        String requestURI = url.substring(16); // Drop "http://localhost"

        // We can use Spring's convenient mock implementation. Defaults to
        // localhost in the URL. Since we only need the URL, we don't need
        // to setup anything else in the request.
        MockHttpServletRequest request = new MockHttpServletRequest("POST", requestURI);

        // Puts the fake request in the current thread for the
        // ServletUriComponentsBuilder to initialize itself from later.
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }
}
