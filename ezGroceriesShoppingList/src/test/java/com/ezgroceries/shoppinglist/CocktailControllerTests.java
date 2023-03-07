package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.cocktail.CocktailControler;
import com.ezgroceries.shoppinglist.cocktail.CocktailDTO;
import com.ezgroceries.shoppinglist.cocktail.CocktailService;
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

public class CocktailControllerTests {
    private CocktailControler controller;
    @BeforeEach
    public void setUp() throws Exception {
        controller = new CocktailControler(new CocktailService());
    }

    @Test
    public void testCreateShoppingList(){
        setupFakeRequest("http://localhost/cocktails");
        ResponseEntity<List<CocktailDTO>> response = controller.getCocktails("Russian");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(2,response.getBody().size());
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
