package com.ezgroceries.shoppinglist.web.cocktails;

import com.ezgroceries.shoppinglist.util.StringSetConverter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "cocktail")
public class CocktailEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private UUID id;

    @Column(name="id_drink")
    String cocktailId;
    String name;

    @Convert(converter = StringSetConverter.class)
    String[] ingredients; //TODO maybe change this later



    public CocktailEntity(){
    }

    public CocktailEntity(String cocktailId, String name, String[] ingredients) {
        this.cocktailId = cocktailId;
        this.name = name;
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


    public void setCocktailId(String cocktailId) {
        this.cocktailId = cocktailId;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }
}
