package com.ezgroceries.shopinglist.meal.persistence;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<MealEntity, UUID> {
    Collection<MealEntity> findByNameContainingIgnoreCase(String searchTerm);
    Collection<MealEntity> findByIdMealIn(Set<String> mealIds);
}
