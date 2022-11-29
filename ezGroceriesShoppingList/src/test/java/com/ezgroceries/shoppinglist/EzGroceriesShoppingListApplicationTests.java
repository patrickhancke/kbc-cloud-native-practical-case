package com.ezgroceries.shoppinglist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EzGroceriesShoppingListApplicationTests {
    private String shoppingUrl = "/shopping-lists";
    private String cocktailUrl = "/cocktails";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void getCocktails(){

        //ResponseEntity<List<Cocktail>> response = restTemplate.getForEntity(cocktailUrl, List<Cocktail>.class);


    }

}
