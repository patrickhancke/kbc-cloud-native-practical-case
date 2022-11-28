package com.ezgroceries.shopinglist.meal.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.ezgroceries.shopinglist.meal.persistence.MealEntity;
import com.ezgroceries.shopinglist.meal.persistence.MealRepository;
import com.ezgroceries.shopinglist.meal.service.MealResponse.MealResource;
import com.ezgroceries.shopinglist.meal.web.Meal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

@DataJpaTest
public class MealServiceTest {
    private static final UUID MEAL_ID = UUID.fromString("08410f17-8a48-4d06-a336-c4513cc86afe");
    private static final UUID MEAL_ID_1 = UUID.fromString("261abccf-7ba9-4706-a7ed-914b5deb04be");
    private static final String SUFFIX = "_1";
    private static final String MEAL_NAME = "meal name";
    private static final String MEAL_IMAGE = "meal image";
    private static final String MEAL_INSTRUCTIONS = "meal instructions";
    private static final String MEAL_CATEGORY = "meal category";
    private static final String MEAL_AREA = "meal area";
    private static final String MEAL_INGREDIENT = "meal ingredient";
    @MockBean
    private MealClient mealClient;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setup() {
        init();
        given(mealClient.searchMeal(any())).willReturn(getResponse());
    }

    @Test
    void testSearchNoParam() {
        MealService mealService = new MealService(mealRepository, mealClient);
        Collection<Meal> search = mealService.search(null);

        assertThat(search, notNullValue());
        assertThat(search.size(), equalTo(3));
    }

    private void init() {
        testEntityManager.persist(createMealEntity());
        testEntityManager.persist(createMealEntity1());
        testEntityManager.flush();
    }

    private MealEntity createMealEntity() {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setId(MEAL_ID);
        mealEntity.setIdMeal("1234");
        mealEntity.setName(MEAL_NAME);
        mealEntity.setImage(MEAL_IMAGE);
        mealEntity.setInstructions(MEAL_INSTRUCTIONS);
        mealEntity.setCategory(MEAL_CATEGORY);
        mealEntity.setArea(MEAL_AREA);
        mealEntity.setIngredients(Set.of(MEAL_INGREDIENT));
        return mealEntity;
    }

    private MealEntity createMealEntity1() {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setId(MEAL_ID_1);
        mealEntity.setIdMeal("5678");
        mealEntity.setName(MEAL_NAME + SUFFIX);
        mealEntity.setImage(MEAL_IMAGE + SUFFIX);
        mealEntity.setInstructions(MEAL_INSTRUCTIONS + SUFFIX);
        mealEntity.setCategory(MEAL_CATEGORY + SUFFIX);
        mealEntity.setArea(MEAL_AREA + SUFFIX);
        mealEntity.setIngredients(Set.of(MEAL_INGREDIENT + SUFFIX));
        return mealEntity;
    }

    private MealResponse getResponse() {
        MealResponse mealResponse = new MealResponse();
        MealResource mealResource = new MealResource();
        mealResource.setIdMeal("1234");

        MealResource mealResource1 = new MealResource();
        mealResource1.setIdMeal("5678");

        MealResource mealResource2 = new MealResource();
        mealResource2.setIdMeal("9999");

        mealResponse.setMeals(List.of(mealResource, mealResource1, mealResource2));
        return mealResponse;
    }

}
