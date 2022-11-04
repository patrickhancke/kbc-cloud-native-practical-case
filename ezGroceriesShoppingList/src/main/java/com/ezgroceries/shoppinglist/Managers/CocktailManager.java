package com.ezgroceries.shoppinglist.Managers;

import com.ezgroceries.shoppinglist.Classes.Cocktail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CocktailManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<Cocktail> getAllCocktails(){

        List<Cocktail> result = new ArrayList<>();
        result.add(new Cocktail("23b3d85a-3928-41c0-a533-6538a71e17c4","Margerita",
                "Cocktail glass","Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg", Arrays.asList("Tequila","Triple sec","Lime juice","Salt")));
        result.add(new Cocktail("d615ec78-fe93-467b-8d26-5d26d8eab073","Blue Margerita",
                "Cocktail glass","Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg", Arrays.asList("Tequila","Blue Curacao","Lime juice","Salt")));
        return result;
    }

    public List<Cocktail> getCocktails(String Cocktail){

        logger.info("GetCocktails");
        logger.info(Cocktail);

        List<Cocktail> result = new ArrayList<>();

        if (Cocktail.equals("Russian")){

            result.add(new Cocktail("23b3d85a-3928-41c0-a533-6538a71e17c4","Margerita",
                    "Cocktail glass","Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                    "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg", Arrays.asList("Tequila","Triple sec","Lime juice","Salt")));
            result.add(new Cocktail("d615ec78-fe93-467b-8d26-5d26d8eab073","Blue Margerita",
                    "Cocktail glass","Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                    "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg", Arrays.asList("Tequila","Blue Curacao","Lime juice","Salt")));

        }

        if (Cocktail.equals ("France")){

            result.add(new Cocktail("23b3d85a-3928-41c0-a533-6538a71e17c4","Margerita2",
                    "Cocktail glass","Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                    "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg", Arrays.asList("Tequila","Triple sec","Lime juice","Salt")));
            result.add(new Cocktail("d615ec78-fe93-467b-8d26-5d26d8eab073","Blue Margerita2",
                    "Cocktail glass","Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                    "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg", Arrays.asList("Tequila","Blue Curacao","Lime juice","Salt")));

        }

        return result;
    }



}
