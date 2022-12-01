package com.ezgroceries.shoppinglist.entities;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class CocktailResource {


    private String cocktailId;
    private String idDrink;
    private String name;
    private List<String> ingredients;
    private String glass;
    private String instructions;
    private String image;

    public CocktailResource(String cocktailId, String name, String glass, String instructions, String image, List<String> ingredients) {
        this.cocktailId = cocktailId;
        this.name = name;
        this.glass = glass;
        this.instructions = instructions;
        this.image = image;
        this.ingredients = ingredients;
    }

    public CocktailResource(String cocktailId, String idDrink, String name) {
        this.cocktailId = cocktailId;
        this.idDrink = idDrink;
        this.name = name;
    }

    public CocktailResource() {

    }

    public CocktailResource(UUID cocktailId, String strDrink, String strGlass, String strInstructions, String strDrinkThumb, List<String> ingredients) {
    }


    public String getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(String cocktailId) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String id_drink) {
        this.idDrink = id_drink;
    }

    public Stream stream() {
        return null;
    }
}

