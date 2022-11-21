package com.ezgroceries.shopinglist.shoppinglist.web;

import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesBadRequestException;
import com.ezgroceries.shopinglist.exceptionhandling.EzGroceriesNotFoundException;
import com.ezgroceries.shopinglist.shoppinglist.ShoppingList;
import com.ezgroceries.shopinglist.shoppinglist.service.ShoppingListsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ShoppingListController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListController.class);

    private final ShoppingListsService shoppingListsService;

    public ShoppingListController(ShoppingListsService shoppingListsService) {
        this.shoppingListsService = shoppingListsService;
    }

    @Operation(summary = "get the requested shopping list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "shopping list found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingList.class))
            }),
            @ApiResponse(responseCode = "400", description = "bad request invalid parameter/s", content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content),
    })
    @ResponseBody
    @GetMapping("/shopping-lists/{shoppingListId}")
    public ShoppingList getShoppingList(@PathVariable(name = "shoppingListId") UUID shoppingListId) {
        return shoppingListsService.getShoppingList(shoppingListId);
    }

    @Operation(summary = "create a shopping list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "shopping list created",
                    content = {@Content(mediaType = "application/json", schema = @Schema)},
                    headers = @Header(name = "Location",
                            description = "the location of the newly created resource",
                            schema = @Schema(type = "url"))),
            @ApiResponse(responseCode = "400", description = "bad request invalid parameter/s", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content),
    })
    @PostMapping("/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "shopping list name",
                    content = @Content(mediaType = "application/json"),
                    required = true)
            @Valid @RequestBody ShoppingList requestShoppingList) {
        ShoppingList shoppingList = shoppingListsService.createShoppingList(requestShoppingList.getName());
        return entityWithLocation(shoppingList.getShoppingListId());
    }

    @Operation(summary = "add cocktails to a shopping list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "shopping list found", content = {
                    @Content(mediaType = "application/json", schema = @Schema)
            }, headers = @Header(name = "Location",
                    description = "the exact location of this newly created resource",
                    schema = @Schema(type = "url"))),
            @ApiResponse(responseCode = "400", description = "bad request invalid parameter/s", content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content),
    })
    @PostMapping("/shopping-lists/{shoppingListId}/cocktails")
    public ResponseEntity<Void> addCocktail(
            @Parameter(description = "shopping list uudi ", required = true)
            @PathVariable UUID shoppingListId,
            @Valid
            @Size(max = 100, message = "parameter max size exceeded")
            @Parameter(description = "cocktail uuid", required = true)
            @RequestParam UUID cocktailId) {
        shoppingListsService.addCocktailToShoppingList(shoppingListId, cocktailId);
        return entityWithLocation(cocktailId);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ EzGroceriesNotFoundException.class })
    public void handleDataIntegrity(Exception ex) {
        LOGGER.error(ex.getMessage());
        // just return empty 404
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ EzGroceriesBadRequestException.class})
    public void handleBadRequest(Exception ex) {
        LOGGER.error(ex.getMessage());
        // just return empty 400
    }

    private ResponseEntity<Void> entityWithLocation(Object resourceId) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{itemId}").buildAndExpand(resourceId.toString()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
