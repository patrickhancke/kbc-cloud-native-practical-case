package com.ezgroceries.shoppinglist.entities;

import com.ezgroceries.shoppinglist.converters.StringSetConverter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

@Entity
@Table(name = "COCKTAIL")
public class CocktailEntity {

    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "ID_DRINK")
    private String idDrink;

    @Column(name = "NAME")
    private String name;

    @Column(name = "INGREDIENTS")
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;



    public CocktailEntity(UUID cocktailId, String name, List<String> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = (Set<String>) ingredients;
    }

    public CocktailEntity() {

    }

    public UUID getCocktailId() {
        return id;
    }

    public void setCocktailId(UUID cocktailId) {
        this.id = cocktailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return (List<String>) ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = (Set<String>) ingredients;
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

