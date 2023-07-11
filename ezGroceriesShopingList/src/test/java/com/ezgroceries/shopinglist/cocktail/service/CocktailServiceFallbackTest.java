package com.ezgroceries.shopinglist.cocktail.service;

import static org.mockito.BDDMockito.given;

import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailRepository;
import com.ezgroceries.shopinglist.cocktail.service.CocktailDBClient.CocktailDBClientFallback;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@DataJpaTest
public class CocktailServiceFallbackTest {
    @MockBean
    private CocktailRepository cocktailRepository;
    @BeforeEach
    void setup() {
        given(cocktailRepository.findByNameContainingIgnoreCase("test"))
                .willReturn(createResponse());
    }

    @Test
    void testFallBackCall() {
        CocktailDBClientFallback cocktailService = new CocktailDBClientFallback(cocktailRepository);
        CocktailDBResponse test = cocktailService.searchCocktails("test");

        Assertions.assertNotNull(test);
        Assertions.assertEquals("fancy cocktail", test.getDrinks().stream().findAny().get().getStrDrink());
        Assertions.assertEquals("tequila", test.getDrinks().stream().findAny().get().getStrIngredient1());
    }

    private Collection<CocktailEntity> createResponse() {
        CocktailEntity cocktail = new CocktailEntity();
        cocktail.setIdDrink("12345");
        cocktail.setName("fancy cocktail");
        cocktail.setIngredients(Set.of("tequila"));

        return List.of(cocktail);
    }
}
