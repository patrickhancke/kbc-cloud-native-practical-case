package com.ezgroceries.shopinglist.shoppinglist.persistence;

import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "ShoppingListEntity")
@Table(name = "SHOPPING_LIST")
public class ShoppingListEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "COCKTAIL_SHOPPING_LIST",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "cocktail")
    )
    private Set<CocktailEntity> cocktails;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<CocktailEntity> getCocktails() {
        return cocktails;
    }

    public void setCocktails(Set<CocktailEntity> cocktails) {
        this.cocktails = cocktails;
    }

    public void addCocktail(CocktailEntity cocktail) {
        if (this.cocktails == null) {
            this.cocktails = new HashSet<>();
        }
        this.cocktails.add(cocktail);

        if (cocktail != null) {
            if (cocktail.getShoppingLists() == null) {
                cocktail.setShoppingLists(new HashSet<>());
            }
            cocktail.getShoppingLists().add(this);
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
        ShoppingListEntity that = (ShoppingListEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(username, that.username)
                && Objects.equals(cocktails, that.cocktails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, cocktails);
    }

    @Override
    public String toString() {
        return "ShoppingListEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", cocktails=" + cocktails +
                '}';
    }
}
