package com.ezgroceries.shoppinglist.web.cocktails;

import java.util.ArrayList;
import java.util.List;

public class CocktailDBResponse {

    private List<DrinkResource> drinks;

    public List<DrinkResource> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkResource> drinks) {
        this.drinks = drinks;
    }

    public static class DrinkResource {

        public DrinkResource(){}

        private String idDrink;
        private String strDrink;
        private String strDrinkAlternate = null;
        private String strTags;
        private String strVideo = null;
        private String strCategory;
        private String strIBA;
        private String strAlcoholic;
        private String strGlass;
        private String strInstructions;
        private String strInstructionsES = null;
        private String strInstructionsDE;
        private String strInstructionsFR = null;
        private String strInstructionsIT;

        //private String strInstructionsZH-HANS = null; //TODO - not accepted, leaving out for now
        //private String strInstructionsZH-HANT = null;
        private String strDrinkThumb;
        private String strIngredient1;
        private String strIngredient2;
        private String strIngredient3;
        private String strIngredient4;
        private String strIngredient5 = null;
        private String strIngredient6 = null;
        private String strIngredient7 = null;
        private String strIngredient8 = null;
        private String strIngredient9 = null;
        private String strIngredient10 = null;
        private String strIngredient11 = null;
        private String strIngredient12 = null;
        private String strIngredient13 = null;
        private String strIngredient14 = null;
        private String strIngredient15 = null;
        private String strMeasure1;
        private String strMeasure2;
        private String strMeasure3;
        private String strMeasure4 = null;
        private String strMeasure5 = null;
        private String strMeasure6 = null;
        private String strMeasure7 = null;
        private String strMeasure8 = null;
        private String strMeasure9 = null;
        private String strMeasure10 = null;
        private String strMeasure11 = null;
        private String strMeasure12 = null;
        private String strMeasure13 = null;
        private String strMeasure14 = null;
        private String strMeasure15 = null;
        private String strImageSource;
        private String strImageAttribution;
        private String strCreativeCommonsConfirmed;
        private String dateModified;


        // Getter Methods

        public String getIdDrink() {
            return idDrink;
        }

        public String getStrDrink() {
            return strDrink;
        }

        public String getStrDrinkAlternate() {
            return strDrinkAlternate;
        }

        public String getStrTags() {
            return strTags;
        }

        public String getStrVideo() {
            return strVideo;
        }

        public String getStrCategory() {
            return strCategory;
        }

        public String getStrIBA() {
            return strIBA;
        }

        public String getStrAlcoholic() {
            return strAlcoholic;
        }

        public String getStrGlass() {
            return strGlass;
        }

        public String getStrInstructions() {
            return strInstructions;
        }

        public String getStrInstructionsES() {
            return strInstructionsES;
        }

        public String getStrInstructionsDE() {
            return strInstructionsDE;
        }

        public String getStrInstructionsFR() {
            return strInstructionsFR;
        }

        public String getStrInstructionsIT() {
            return strInstructionsIT;
        }

        /*
        public String getStrInstructionsZH - HANS() {
            return strInstructionsZH - HANS;
        }

        public String getStrInstructionsZH - HANT() {
            return strInstructionsZH - HANT;
        }
         */

        public String getStrDrinkThumb() {
            return strDrinkThumb;
        }

        public String[] getStrIngredients(){
            ArrayList<String> ingredients = new ArrayList<>();
            ingredients.add(getStrIngredient1());
            if (getStrIngredient2()!=null)
                ingredients.add(getStrIngredient2());
            if (getStrIngredient3()!=null)
                ingredients.add(getStrIngredient3());
            if (getStrIngredient4()!=null)
                ingredients.add(getStrIngredient4());
            if (getStrIngredient5()!=null)
                ingredients.add(getStrIngredient5());
            if (getStrIngredient6()!=null)
                ingredients.add(getStrIngredient6());
            if (getStrIngredient7()!=null)
                ingredients.add(getStrIngredient7());
            if (getStrIngredient8()!=null)
                ingredients.add(getStrIngredient8());
            if (getStrIngredient9()!=null)
                ingredients.add(getStrIngredient9());
            if (getStrIngredient10()!=null)
                ingredients.add(getStrIngredient10());
            if (getStrIngredient11()!=null)
                ingredients.add(getStrIngredient11());
            if (getStrIngredient12()!=null)
                ingredients.add(getStrIngredient12());
            String[] arr = new String[ingredients.size()];
            return ingredients.toArray(arr);
        }
        public String getStrIngredient1() {
            return strIngredient1;
        }

        public String getStrIngredient2() {
            return strIngredient2;
        }

        public String getStrIngredient3() {
            return strIngredient3;
        }

        public String getStrIngredient4() {
            return strIngredient4;
        }

        public String getStrIngredient5() {
            return strIngredient5;
        }

        public String getStrIngredient6() {
            return strIngredient6;
        }

        public String getStrIngredient7() {
            return strIngredient7;
        }

        public String getStrIngredient8() {
            return strIngredient8;
        }

        public String getStrIngredient9() {
            return strIngredient9;
        }

        public String getStrIngredient10() {
            return strIngredient10;
        }

        public String getStrIngredient11() {
            return strIngredient11;
        }

        public String getStrIngredient12() {
            return strIngredient12;
        }

        public String getStrIngredient13() {
            return strIngredient13;
        }

        public String getStrIngredient14() {
            return strIngredient14;
        }

        public String getStrIngredient15() {
            return strIngredient15;
        }

        public String getStrMeasure1() {
            return strMeasure1;
        }

        public String getStrMeasure2() {
            return strMeasure2;
        }

        public String getStrMeasure3() {
            return strMeasure3;
        }

        public String getStrMeasure4() {
            return strMeasure4;
        }

        public String getStrMeasure5() {
            return strMeasure5;
        }

        public String getStrMeasure6() {
            return strMeasure6;
        }

        public String getStrMeasure7() {
            return strMeasure7;
        }

        public String getStrMeasure8() {
            return strMeasure8;
        }

        public String getStrMeasure9() {
            return strMeasure9;
        }

        public String getStrMeasure10() {
            return strMeasure10;
        }

        public String getStrMeasure11() {
            return strMeasure11;
        }

        public String getStrMeasure12() {
            return strMeasure12;
        }

        public String getStrMeasure13() {
            return strMeasure13;
        }

        public String getStrMeasure14() {
            return strMeasure14;
        }

        public String getStrMeasure15() {
            return strMeasure15;
        }

        public String getStrImageSource() {
            return strImageSource;
        }

        public String getStrImageAttribution() {
            return strImageAttribution;
        }

        public String getStrCreativeCommonsConfirmed() {
            return strCreativeCommonsConfirmed;
        }

        public String getDateModified() {
            return dateModified;
        }

        // Setter Methods

        public void setIdDrink(String idDrink) {
            this.idDrink = idDrink;
        }

        public void setStrDrink(String strDrink) {
            this.strDrink = strDrink;
        }

        public void setStrDrinkAlternate(String strDrinkAlternate) {
            this.strDrinkAlternate = strDrinkAlternate;
        }

        public void setStrTags(String strTags) {
            this.strTags = strTags;
        }

        public void setStrVideo(String strVideo) {
            this.strVideo = strVideo;
        }

        public void setStrCategory(String strCategory) {
            this.strCategory = strCategory;
        }

        public void setStrIBA(String strIBA) {
            this.strIBA = strIBA;
        }

        public void setStrAlcoholic(String strAlcoholic) {
            this.strAlcoholic = strAlcoholic;
        }

        public void setStrGlass(String strGlass) {
            this.strGlass = strGlass;
        }

        public void setStrInstructions(String strInstructions) {
            this.strInstructions = strInstructions;
        }

        public void setStrInstructionsES(String strInstructionsES) {
            this.strInstructionsES = strInstructionsES;
        }

        public void setStrInstructionsDE(String strInstructionsDE) {
            this.strInstructionsDE = strInstructionsDE;
        }

        public void setStrInstructionsFR(String strInstructionsFR) {
            this.strInstructionsFR = strInstructionsFR;
        }

        public void setStrInstructionsIT(String strInstructionsIT) {
            this.strInstructionsIT = strInstructionsIT;
        }

        /*
        public void setStrInstructionsZH - HANS(String strInstructionsZH - HANS) {
            this.strInstructionsZH - HANS = strInstructionsZH - HANS;
        }

        public void setStrInstructionsZH - HANT(String strInstructionsZH - HANT) {
            this.strInstructionsZH - HANT = strInstructionsZH - HANT;
        }
         */

        public void setStrDrinkThumb(String strDrinkThumb) {
            this.strDrinkThumb = strDrinkThumb;
        }

        public void setStrIngredient1(String strIngredient1) {
            this.strIngredient1 = strIngredient1;
        }

        public void setStrIngredient2(String strIngredient2) {
            this.strIngredient2 = strIngredient2;
        }

        public void setStrIngredient3(String strIngredient3) {
            this.strIngredient3 = strIngredient3;
        }

        public void setStrIngredient4(String strIngredient4) {
            this.strIngredient4 = strIngredient4;
        }

        public void setStrIngredient5(String strIngredient5) {
            this.strIngredient5 = strIngredient5;
        }

        public void setStrIngredient6(String strIngredient6) {
            this.strIngredient6 = strIngredient6;
        }

        public void setStrIngredient7(String strIngredient7) {
            this.strIngredient7 = strIngredient7;
        }

        public void setStrIngredient8(String strIngredient8) {
            this.strIngredient8 = strIngredient8;
        }

        public void setStrIngredient9(String strIngredient9) {
            this.strIngredient9 = strIngredient9;
        }

        public void setStrIngredient10(String strIngredient10) {
            this.strIngredient10 = strIngredient10;
        }

        public void setStrIngredient11(String strIngredient11) {
            this.strIngredient11 = strIngredient11;
        }

        public void setStrIngredient12(String strIngredient12) {
            this.strIngredient12 = strIngredient12;
        }

        public void setStrIngredient13(String strIngredient13) {
            this.strIngredient13 = strIngredient13;
        }

        public void setStrIngredient14(String strIngredient14) {
            this.strIngredient14 = strIngredient14;
        }

        public void setStrIngredient15(String strIngredient15) {
            this.strIngredient15 = strIngredient15;
        }

        public void setStrMeasure1(String strMeasure1) {
            this.strMeasure1 = strMeasure1;
        }

        public void setStrMeasure2(String strMeasure2) {
            this.strMeasure2 = strMeasure2;
        }

        public void setStrMeasure3(String strMeasure3) {
            this.strMeasure3 = strMeasure3;
        }

        public void setStrMeasure4(String strMeasure4) {
            this.strMeasure4 = strMeasure4;
        }

        public void setStrMeasure5(String strMeasure5) {
            this.strMeasure5 = strMeasure5;
        }

        public void setStrMeasure6(String strMeasure6) {
            this.strMeasure6 = strMeasure6;
        }

        public void setStrMeasure7(String strMeasure7) {
            this.strMeasure7 = strMeasure7;
        }

        public void setStrMeasure8(String strMeasure8) {
            this.strMeasure8 = strMeasure8;
        }

        public void setStrMeasure9(String strMeasure9) {
            this.strMeasure9 = strMeasure9;
        }

        public void setStrMeasure10(String strMeasure10) {
            this.strMeasure10 = strMeasure10;
        }

        public void setStrMeasure11(String strMeasure11) {
            this.strMeasure11 = strMeasure11;
        }

        public void setStrMeasure12(String strMeasure12) {
            this.strMeasure12 = strMeasure12;
        }

        public void setStrMeasure13(String strMeasure13) {
            this.strMeasure13 = strMeasure13;
        }

        public void setStrMeasure14(String strMeasure14) {
            this.strMeasure14 = strMeasure14;
        }

        public void setStrMeasure15(String strMeasure15) {
            this.strMeasure15 = strMeasure15;
        }

        public void setStrImageSource(String strImageSource) {
            this.strImageSource = strImageSource;
        }

        public void setStrImageAttribution(String strImageAttribution) {
            this.strImageAttribution = strImageAttribution;
        }

        public void setStrCreativeCommonsConfirmed(String strCreativeCommonsConfirmed) {
            this.strCreativeCommonsConfirmed = strCreativeCommonsConfirmed;
        }

        public void setDateModified(String dateModified) {
            this.dateModified = dateModified;
        }
    }
}