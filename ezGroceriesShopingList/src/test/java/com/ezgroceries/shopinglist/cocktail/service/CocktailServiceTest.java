package com.ezgroceries.shopinglist.cocktail.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailRepository;
import java.util.Collection;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;

@DataJpaTest
public class CocktailServiceTest {
    @MockBean
    private CocktailDBClient cocktailDBClient;
    @MockBean
    private KafkaTemplate<String, CocktailEntity> kafkaTemplate;
    @Autowired
    private CocktailRepository cocktailRepository;

    @BeforeEach
    void setup() {
        given(cocktailDBClient.searchCocktails(any())).willReturn(createResponse());
        given(kafkaTemplate.send(eq("quickstart-events"), any()))
                .willReturn(Mockito.mock(ListenableFuture.class));
    }

    @Test
    void testSearch() {
        CocktailService cocktailService = new CocktailService(cocktailRepository, cocktailDBClient, kafkaTemplate);
        Collection<Cocktail> test = cocktailService.searchByTerm("test");

        Assertions.assertNotNull(test);
        Assertions.assertEquals("fancy cocktail", test.stream().findAny().get().getName());
        Assertions.assertEquals("tequila", test.stream().findAny().get().getIngredients().stream().findAny().get());
    }

    private CocktailDBResponse createResponse() {
        CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();
        DrinkResource drink = new DrinkResource();
        drink.setIdDrink("12345");
        drink.setStrDrink("fancy cocktail");
        drink.setStrIngredient1("tequila");

        cocktailDBResponse.setDrinks(Collections.singletonList(drink));
        return cocktailDBResponse;
    }

}
