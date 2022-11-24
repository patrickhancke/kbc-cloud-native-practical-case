package com.ezgroceries.shoppinglist.web.shoppinglists;

import javax.persistence.*;
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

    public ShoppingList(){
    }

    public ShoppingList(UUID id, String name){
        this.id = id;
        this.name = name;
    }

    public ShoppingList(UUID id, String name, String[] ingredients){
        this.id = id;
        this.name = name;
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
}
