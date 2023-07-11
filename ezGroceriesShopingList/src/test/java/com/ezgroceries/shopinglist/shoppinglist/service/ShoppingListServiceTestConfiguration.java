package com.ezgroceries.shopinglist.shoppinglist.service;

import java.util.UUID;

public class ShoppingListServiceTestConfiguration {
    private static final String SHOPPING_LIST_ID = "e97e0d84-eca3-4829-a647-0d4d38a618d5";
    protected static final UUID SHOPPING_LIST_UUID = UUID.fromString(SHOPPING_LIST_ID);
    private static final String COCKTAIL_1_ID = "e97e0d84-eca3-4829-a647-0d4d38a618d5";
    public static final UUID COCKTAIL_1_UUID = UUID.fromString(COCKTAIL_1_ID);
    private static final String COCKTAIL_2_ID = "05bd188c-dae6-4d40-aa14-82c6f155731b";
    public static final UUID COCKTAIL_2_UUID = UUID.fromString(COCKTAIL_2_ID);
    public static final UUID COCKTAIL_3_UUID = UUID.fromString("fe2f618f-c12e-4f81-afef-46ca5c0da2c8");
    protected static final String SHOPPING_LIST_NAME = "Stephanie's birthday";
    public static final String COCKTAIL_1_DRINK_ID = "1234";
    public static final String COCKTAIL_1_NAME = "some name here";
    public static final String COCKTAIL_1_GLASS = "glass";
    public static final String COCKTAIL_1_IMAGE = "https://image.address.here";
    public static final String COCKTAIL_1_INSTRUCTIONS = "instructions here";
    public static final String COCKTAIL_1_INGREDIENT = "ingredient 1 here";
    public static final String COCKTAIL_1_INGREDIENT2 = "ingredient 2 here";
    private static final String PREFIX_2 = "2_";
    public static final String COCKTAIL_2_NAME = PREFIX_2 + COCKTAIL_1_NAME;
    public static final String COCKTAIL_2_GLASS = PREFIX_2 + COCKTAIL_1_GLASS;
    public static final String COCKTAIL_2_IMAGE = COCKTAIL_1_IMAGE + PREFIX_2;
    public static final String COCKTAIL_2_INSTRUCTIONS = PREFIX_2 + COCKTAIL_1_INSTRUCTIONS;
    public static final String COCKTAIL_2_INGREDIENT = PREFIX_2 + COCKTAIL_1_INGREDIENT;
    public static final String COCKTAIL_2_INGREDIENT2 = PREFIX_2 + COCKTAIL_1_INGREDIENT2;
    private static final String SUFFIX_3 = "_3";
    public static final String COCKTAIL_3_NAME = COCKTAIL_2_NAME + SUFFIX_3;
    public static final String COCKTAIL_3_GLASS = COCKTAIL_2_GLASS + SUFFIX_3;
    public static final String COCKTAIL_3_IMAGE = COCKTAIL_2_IMAGE + SUFFIX_3;
    public static final String COCKTAIL_3_INSTRUCTIONS = COCKTAIL_2_INSTRUCTIONS + SUFFIX_3;
    public static final String COCKTAIL_3_INGREDIENT = COCKTAIL_2_INGREDIENT + SUFFIX_3;
    public static final String COCKTAIL_3_INGREDIENT2 = COCKTAIL_2_INGREDIENT2 + SUFFIX_3;
    public static final UUID MEAL_ID = UUID.fromString("e97e0d84-eca3-4829-a647-0d4d38a618d5");
    public static final String MEAL_ID_MEAL = "1234";
    public static final String MEAL_NAME = "meal name";
    public static final String MEAL_IMAGE = "meal image";
    public static final String MEAL_INSTRUCTIONS = "meal instruction";
    public static final String MEAL_CATEGORY = "meal category";
    public static final String MEAL_AREA = "meal area";
    public static final String MEAL_INGREDIENT = "meal ingredient";
    private static final String SUFFIX = "_1";
    public static final UUID MEAL_ID_1 = UUID.fromString("e98e0d84-eca3-4829-a647-0d4d38a618d5");
    public static final String MEAL_ID_MEAL_1 = "5678";
    public static final String MEAL_NAME_1 = "meal name" + SUFFIX;
    public static final String MEAL_IMAGE_1 = "meal image" + SUFFIX;
    public static final String MEAL_INSTRUCTIONS_1 = "meal instruction" + SUFFIX;
    public static final String MEAL_CATEGORY_1 = "meal category" + SUFFIX;
    public static final String MEAL_AREA_1 = "meal area" + SUFFIX;
    public static final String MEAL_INGREDIENT_1 = "meal ingredient" + SUFFIX;
}