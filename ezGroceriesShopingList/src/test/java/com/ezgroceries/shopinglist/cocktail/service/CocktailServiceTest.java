package com.ezgroceries.shopinglist.cocktail.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse;
import com.ezgroceries.shopinglist.cocktail.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

@DataJpaTest
public class CocktailServiceTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @MockBean
    private CocktailDBClient cocktailDBClient;

    @Autowired
    private CocktailRepository cocktailRepository;

    @BeforeEach
    void setup() {
        given(cocktailDBClient.searchCocktails(any())).willReturn(createResponse());
    }

    @Test
    void testSearch() {
        CocktailService cocktailService = new CocktailService(cocktailRepository, cocktailDBClient);
        Collection<Cocktail> test = cocktailService.searchByTerm("test");

        Assertions.assertNotNull(test);
        Assertions.assertEquals("fancy cocktail", test.stream().findAny().get().getName());
        Assertions.assertEquals("tequila", test.stream().findAny().get().getIngredients().stream().findAny().get());
    }

    @Test
    void testFindById() {
        CocktailEntity cocktailEntity = new CocktailEntity();

        cocktailEntity.setId(UUID.randomUUID());
        cocktailEntity.setName("random name");
        CocktailEntity persist = testEntityManager.persist(cocktailEntity);
        testEntityManager.flush();

        cocktailRepository.findById(persist.getId());

        Assert.notNull(persist, "kor");
        Assertions.assertEquals(cocktailEntity.getId(), persist.getId());
        Assertions.assertEquals(cocktailEntity.getName(), persist.getName());
    }

    @Test
    void testFindByDrunkId() {
        CocktailEntity cocktailEntity = new CocktailEntity();
        cocktailEntity.setId(UUID.randomUUID());
        cocktailEntity.setIdDrink("kor");
        cocktailEntity.setName("some name here");
        testEntityManager.persist(cocktailEntity);
        testEntityManager.flush();

        Collection<CocktailEntity> kor = cocktailRepository.findByIdDrinkIn(Collections.singleton("kor"));
        Assertions.assertNotNull(kor);
        Assertions.assertEquals(1, kor.size());
        Assertions.assertEquals("kor", kor.stream().findAny().get().getIdDrink());

    }

    @Test
    void testFindByName() {
        CocktailEntity cocktailEntity = new CocktailEntity();
        cocktailEntity.setId(UUID.randomUUID());
        cocktailEntity.setName("some name here");
        testEntityManager.persist(cocktailEntity);
        testEntityManager.flush();

        CocktailEntity foundByName = cocktailRepository.findByName("some name here");
        Assertions.assertNotNull(foundByName);
        Assertions.assertEquals(cocktailEntity.getId(), foundByName.getId());
        Assertions.assertEquals(cocktailEntity.getName(), foundByName.getName());
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
