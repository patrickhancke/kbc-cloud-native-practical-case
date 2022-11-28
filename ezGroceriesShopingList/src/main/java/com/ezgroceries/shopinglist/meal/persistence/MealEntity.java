package com.ezgroceries.shopinglist.meal.persistence;

import com.ezgroceries.shopinglist.cocktail.persistence.StringSetConverter;
import com.ezgroceries.shopinglist.shoppinglist.persistence.ShoppingListEntity;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MEAL")
public class MealEntity {
    @Id
    private UUID id;

    @Column(name = "ID_MEAL")
    private String idMeal;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "INSTRUCTIONS")
    private String instructions;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "AREA")
    private String area;

    @Column(name = "INGREDIENTS")
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    @ManyToMany(mappedBy = "meals")
    private Set<ShoppingListEntity> shoppingLists;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<ShoppingListEntity> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(Set<ShoppingListEntity> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MealEntity that = (MealEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(idMeal, that.idMeal) && Objects.equals(name, that.name)
                && Objects.equals(image, that.image) && Objects.equals(instructions, that.instructions) && Objects.equals(
                category, that.category) && Objects.equals(area, that.area) && Objects.equals(ingredients, that.ingredients)
                && Objects.equals(shoppingLists, that.shoppingLists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idMeal, name, image, instructions, category, area, ingredients, shoppingLists);
    }

    @Override
    public String toString() {
        return "MealEntity{" +
                "id=" + id +
                ", idMeal='" + idMeal + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", instructions='" + instructions + '\'' +
                ", category='" + category + '\'' +
                ", area='" + area + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", shoppingLists=" + shoppingLists +
                '}';
    }
}
