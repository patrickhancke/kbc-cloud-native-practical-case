package com.ezgroceries.shopinglist.cocktail;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Cocktail implements Serializable {

    private UUID cocktailId;
    private String name;
    private String glass;
    private String instructions;
    private URI image;
    private Collection<String> ingredients = new ArrayList<>();

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public URI getImage() {
        return image;
    }

    public void setImage(URI image) {
        this.image = image;
    }

    public Collection<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<String> ingredients) {
        this.ingredients = ingredients;
    }


}
