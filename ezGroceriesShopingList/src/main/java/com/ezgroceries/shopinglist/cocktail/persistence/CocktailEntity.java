package com.ezgroceries.shopinglist.cocktail.persistence;

import com.ezgroceries.shopinglist.shoppinglist.persistence.ShoppingListEntity;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name = "COCKTAIL")
@Entity(name = "CocktailEntity")
public class CocktailEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "id_drunk")
    private String idDrunk;

    @Column(name = "name")
    private String name;

    @Column(name = "ingredients")
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    @ManyToMany(mappedBy = "cocktails")
    private Set<ShoppingListEntity> shoppingLists;

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

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getIdDrunk() {
        return idDrunk;
    }

    public void setIdDrunk(String idDrunk) {
        this.idDrunk = idDrunk;
    }

    public Set<ShoppingListEntity> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(Set<ShoppingListEntity> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public void addShoppingList(ShoppingListEntity shoppingList) {
        if (this.shoppingLists != null) {
            this.shoppingLists = new HashSet<>();
            shoppingLists.add(shoppingList);
        }

        if (shoppingList != null)
        {
            shoppingList.getCocktails().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CocktailEntity that = (CocktailEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(idDrunk, that.idDrunk) && Objects.equals(name, that.name)
                && Objects.equals(ingredients, that.ingredients) && Objects.equals(shoppingLists, that.shoppingLists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idDrunk, name, ingredients, shoppingLists);
    }

    @Override
    public String toString() {
        return "CocktailEntity{" +
                "id=" + id +
                ", idDrunk='" + idDrunk + '\'' +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", shoppingLists=" + shoppingLists +
                '}';
    }
}
