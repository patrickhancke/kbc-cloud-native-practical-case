package com.ezgroceries.shoppinglist.web.cocktails;


import java.util.List;

public interface CocktailService {

    List<Cocktail> getAllCocktails();

    List<Cocktail> searchCocktail(String search);
}
