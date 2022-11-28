package com.ezgroceries.shopinglist.meal.service;

import com.ezgroceries.shopinglist.meal.persistence.MealEntity;
import com.ezgroceries.shopinglist.meal.persistence.MealRepository;
import com.ezgroceries.shopinglist.meal.service.MealResponse.MealResource;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "mealClient", url = "https://themealdb.com/api/json/v1/1", fallback = MealClient.MealClientFallback.class)
public interface MealClient {
    @GetMapping(value = "search.php")
    MealResponse searchMeal(@RequestParam("s") String search);

    @Component
    class MealClientFallback implements MealClient {
        private static final Logger LOGGER = LoggerFactory.getLogger(MealClientFallback.class);
        private final MealRepository mealRepository;

        public MealClientFallback(MealRepository mealRepository) {
            this.mealRepository = mealRepository;
        }

        @Override
        public MealResponse searchMeal(String search) {
            LOGGER.debug("meal fallback client call");
            Collection<MealEntity> mealsFound = mealRepository.findByNameContainingIgnoreCase(search);

            MealResponse mealResponse = new MealResponse();
            mealResponse.setMeals(mealsFound.stream().map(mealEntity -> {
                MealResource mealResource = new MealResource();
                mealResource.setIdMeal(mealEntity.getIdMeal());
                mealResource.setStrMeal(mealEntity.getName());
                mealResource.setStrMealThumb(mealEntity.getImage());
                mealResource.setStrInstructions(mealEntity.getInstructions());
                mealResource.setStrCategory(mealEntity.getCategory());
                mealResource.setStrArea(mealEntity.getArea());
                Set<String> ingredients = mealEntity.getIngredients();
                if (ingredients != null && !ingredients.isEmpty()) {
                    Iterator<String> ingredientIterator = ingredients.iterator();
                    if (ingredientIterator.hasNext()) {
                        mealResource.setStrIngredient1(ingredientIterator.next());
                    }
                    if (ingredientIterator.hasNext()) {
                        mealResource.setStrIngredient2(ingredientIterator.next());
                    }
                    if (ingredientIterator.hasNext()) {
                        mealResource.setStrIngredient3(ingredientIterator.next());
                    }
                }

                return mealResource;
            }).collect(Collectors.toList()));

            return mealResponse;
        }
    }
}
