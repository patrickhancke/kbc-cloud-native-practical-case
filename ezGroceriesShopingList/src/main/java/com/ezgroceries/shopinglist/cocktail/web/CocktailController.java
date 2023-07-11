package com.ezgroceries.shopinglist.cocktail.web;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import com.ezgroceries.shopinglist.cocktail.service.CocktailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CocktailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CocktailController.class);

    private final CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @Operation(summary = "get all cocktails")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retrieve all cocktails matching the request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Cocktail[].class))
            }),
            @ApiResponse(responseCode = "400", description = "bad request invalid parameter/s", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content),
    })
    @ResponseBody
    @GetMapping("/cocktails")
    public Collection<Cocktail> getCocktails(
            @Valid
            @Size(max = 100, message = "maximum param length reached")
            @RequestParam(value = "searchTerm", required = false)
                    String searchTerm) {
        LOGGER.info("searchTerm: {}", searchTerm);
        return cocktailService.searchByTerm(searchTerm);
    }
}
