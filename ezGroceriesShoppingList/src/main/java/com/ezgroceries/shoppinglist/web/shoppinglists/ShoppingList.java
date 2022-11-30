package com.ezgroceries.shoppinglist.web.shoppinglists;

import com.ezgroceries.shoppinglist.web.cocktails.Cocktail;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "shopping_list")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private UUID id;

    @Column(name="name")
    String name;

    @ManyToMany
    @JoinTable(
            name = "COCKTAIL_SHOPPING_LIST",
            joinColumns = @JoinColumn(name = "SHOPPING_LIST_ID"),
            inverseJoinColumns = @JoinColumn(name = "COCKTAIL_ID"))
    Set<Cocktail> cocktails;

    @Transient
    Set<String> ingredients = new HashSet<>();

    public ShoppingList(){
    }

    public ShoppingList(UUID id, String name){
        this.id = id;
        this.name = name;
        this.cocktails = new HashSet<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Cocktail> getCocktails() {
        return cocktails;
    }

    public void setCocktails(Set<Cocktail> cocktails) {
        this.cocktails = cocktails;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
