package com.ezgroceries.shopinglist.meal.web;

import com.ezgroceries.shopinglist.meal.service.MealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MealController.class);
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @Operation(summary = "get all meals satisfying the given criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retrieve all meals matching the request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Meal[].class))
            }),
            @ApiResponse(responseCode = "400", description = "bad request invalid parameter/s", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content),
    })
    @GetMapping("/meals")
    public Collection<Meal> getMeals(@RequestParam(value = "searchTerm", required = false) String searchTerm) {
        LOGGER.debug("search term: {}", searchTerm);
        return mealService.search(searchTerm);
    }
}
