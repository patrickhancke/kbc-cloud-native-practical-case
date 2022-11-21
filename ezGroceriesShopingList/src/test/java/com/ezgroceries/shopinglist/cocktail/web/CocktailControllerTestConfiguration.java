package com.ezgroceries.shopinglist.cocktail.web;

import com.ezgroceries.shopinglist.cocktail.Cocktail;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import org.springframework.context.annotation.Bean;

public class CocktailControllerTestConfiguration {

    @Bean
    public Collection<Cocktail> cocktails() {
        var cocktail = new Cocktail();
        cocktail.setName("Margerita");
        cocktail.setGlass("Cocktail glass");
        cocktail.setInstructions("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..");
        cocktail.setIngredients(Set.of("Tequila", "Triple sec", "Lime juice"));
        try {
            cocktail.setImage(new URI("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        var cocktail1 = new Cocktail();
        cocktail1.setName("Blue Margerita");
        cocktail1.setGlass("Cocktail glass");
        cocktail1.setInstructions("Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..");
        cocktail1.setIngredients(Set.of("Tequila", "Blue Curacao", "Lime juice"));
        try {
            cocktail1.setImage(new URI("https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        List<Cocktail> cocktails = Arrays.asList(cocktail, cocktail1);
        cocktails.sort(Comparator.comparing(Cocktail::getName));

        return cocktails;
    }
}
