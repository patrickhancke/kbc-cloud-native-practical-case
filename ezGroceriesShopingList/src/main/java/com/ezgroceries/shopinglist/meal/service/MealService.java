package com.ezgroceries.shopinglist.meal.service;

import com.ezgroceries.shopinglist.meal.persistence.MealEntity;
import com.ezgroceries.shopinglist.meal.persistence.MealRepository;
import com.ezgroceries.shopinglist.meal.service.MealResponse.MealResource;
import com.ezgroceries.shopinglist.meal.web.Meal;
import com.ezgroceries.shopinglist.meal.web.MealFactory;
import com.ezgroceries.shopinglist.meal.web.MealTransformer;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MealService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MealService.class);
    private final MealRepository mealRepository;
    private final MealClient mealClient;

    public MealService(MealRepository mealRepository, MealClient mealClient) {
        this.mealRepository = mealRepository;
        this.mealClient = mealClient;
    }

    public Collection<Meal> search(String searchTerm) {
        MealResponse mealResponse = mealClient.searchMeal(searchTerm);
        if (mealResponse == null) {
            return Collections.emptyList();
        }

        Collection<MealEntity> found = mealRepository.findByIdMealIn(mealResponse.getMeals()
                .stream().map(MealResource::getIdMeal).collect(Collectors.toSet()));

        if (Objects.equals(found.size(), mealResponse.getMeals().size())) {
            return found.stream().map(MealTransformer.getInstance()::transform).collect(Collectors.toList());
        }

        List<String> existingMealIds = found.stream().map(MealEntity::getIdMeal).collect(Collectors.toList());
        Collection<MealEntity> mealEntities = populateMealTable(mealResponse.getMeals().stream()
                .filter(meal -> !existingMealIds.contains(meal.getIdMeal()))
                .collect(Collectors.toSet()));
        mealEntities.addAll(found);
        return mealEntities.stream().map(MealTransformer.getInstance()::transform).collect(Collectors.toList());

    }

    private Collection<MealEntity> populateMealTable(Collection<MealResource> meals) {
        List<MealEntity> entities = meals.stream().map(MealFactory.getInstance()::create).collect(Collectors.toList());
        Collection<MealEntity> mealEntities = (Collection<MealEntity>) mealRepository.saveAll(entities);
        LOGGER.debug("meals table populated with {}", mealEntities.size());

        return mealEntities;
    }
}
