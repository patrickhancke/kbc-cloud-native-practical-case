package com.ezgroceries.shoppinglist.Classes;

import com.ezgroceries.shoppinglist.Controllers.CocktailDBClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

    public class CocktailDBResponse {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<DrinkResource> drinks;

    public List<DrinkResource> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkResource> drinks) {
        this.drinks = drinks;
    }

    public static class DrinkResource {
        private String idDrink;
        private String strDrink;
        private String strGlass;
        private String strInstructions;
        private String strDrinkThumb;
        private String strIngredient1;
        private String strIngredient2;
        private String strIngredient3;


        public String getIdDrink() {
            return idDrink;
        }

        public void setIdDrink(String idDrink) {
            this.idDrink = idDrink;
        }

        public String getStrDrink() {
            return strDrink;
        }

        public void setStrDrink(String strDrink) {
            this.strDrink = strDrink;
        }

        public String getStrGlass() {
            return strGlass;
        }

        public void setStrGlass(String strGlass) {
            this.strGlass = strGlass;
        }

        public String getStrInstructions() {
            return strInstructions;
        }

        public void setStrInstructions(String strInstructions) {
            this.strInstructions = strInstructions;
        }

        public String getStrDrinkThumb() {
            return strDrinkThumb;
        }

        public void setStrDrinkThumb(String strDrinkThumb) {
            this.strDrinkThumb = strDrinkThumb;
        }

        public String getStrIngredient1() {
            return strIngredient1;
        }

        public void setStrIngredient1(String strIngredient1) {
            this.strIngredient1 = strIngredient1;
        }

        public String getStrIngredient2() {
            return strIngredient2;
        }

        public void setStrIngredient2(String strIngredient2) {
            this.strIngredient2 = strIngredient2;
        }

        public String getStrIngredient3() {
            return strIngredient3;
        }

        public void setStrIngredient3(String strIngredient3) {
            this.strIngredient3 = strIngredient3;
        }
    }
    //other methods here
}



