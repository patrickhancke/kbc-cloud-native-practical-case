package com.ezgroceries.shoppinglist.web.cocktails;

public class Cocktail {

    String cocktailId;
    String name;
    String glass;
    String instructions;
    String image;
    String[] ingredients; //TODO maybe change this later

    public Cocktail(){
    }

    public Cocktail(String cocktailId, String name, String glass, String instructions, String image, String[] ingredients) {
        this.cocktailId = cocktailId;
        this.name = name;
        this.glass = glass;
        this.instructions = instructions;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getCocktailId() {
        return cocktailId;
    }

    public String getName() {
        return name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String getImage() {
        return image;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getGlass() {
        return glass;
    }

    public void setCocktailId(String cocktailId) {
        this.cocktailId = cocktailId;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public void setName(String name) {
        this.name = name;
    }
}
