# Lab 03: external API
It's time to gradually replace our dummy API data with the real thing, we'll start to integrate with the external API's
provided by ``cocktaildb`` and ``mealdb``:

* https://www.thecocktaildb.com/api.php
* https://www.themealdb.com/api.php

## OpenFeign

There are dozens of frameworks and libraries that we can use to call an external http API. In our case we're going to
use the [OpenFeign http client](https://github.com/OpenFeign/feign).

We make this choice mainly due to:

* Low verbosity and fast implementation
* Spring integration through Spring Cloud
* Support for common integration patterns, e.g. Circuit Breaker

## Spring Cloud

This is a good moment to introduce [Spring Cloud](https://spring.io/projects/spring-cloud).

Relevant for adding our OpenFeign dependency is the
BOM ([Bill of Materials](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#bill-of-materials-bom-poms))
and release train it provides. This assures us that various Spring Cloud projects are managed through one release train
and are compatible with our Spring Boot version.

To add the correct BOM you should pick
the [release train which matches your Spring Boot version](https://spring.io/projects/spring-cloud) and define it in
your ``pom.xml``:

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring.cloud-version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

Then we add the actual OpenFeign dependency, notice this is a Spring Cloud starter. Similar to the previous Spring Boot
starters, we don't have to specify the version as it is defined in the BOM:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

# Feign CocktailDB client
Defining external API's in Feign is done in a declarative way, now we'll define all the external calls we deem
necessary.

To enable Spring to detect Feign clients we need to enable this through the ``@EnableFeignClients`` annotation on our
main Spring Boot Application class:

```java
@SpringBootApplication
@EnableFeignClients
public class EzGroceriesShoppingListApplication {
    //other methods left out here
}
```

### Search cocktails
Study the response of the search cocktail API:

Request: ``GET https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita``

Response (excerpt)

```json
{
  "drinks": [
    {
      "idDrink": "13060",
      "strDrink": "Margarita",
      "strGlass": "Cocktail glass",
      "strInstructions": "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten only the outer rim and sprinkle the salt on it. The salt should present to the lips of the imbiber and never mix into the cocktail. Shake the other ingredients with ice, then carefully pour into the glass.",
      "strDrinkThumb": "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
      "strIngredient1": "Tequila",
      "strIngredient2": "Triple sec",
      "strIngredient3": "Lime juice",
      "strIngredient4": "Salt",
      "strIngredient5": "",
      "strIngredient6": "",
      "strIngredient7": "",
      ...
    },
    ...
```

We need to define the response fields we need in a Java class, for example (excerpt):

```java
public class CocktailDBResponse {

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
    }
    //other methods here
}
```

Example Feign client implementation:

```java
@Component
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1")
public interface CocktailDBClient {

    @GetMapping(value = "search.php")
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);

}
```

### Integrate in our API

Now we can integrate this search into our API, this means replacing our dummy resources with this search call and
transforming their responses into our responses.

After this implementation, the below example request to our API should be up and running:

Request: ``GET localhost:8080/cocktails?search=russian``

Response:

```json
[
  {
    "cocktailId": "88e57cc2-09d2-43ab-9ff3-de14811523a1",
    "name": "Black Russian",
    "glass": "Old-fashioned glass",
    "instructions": "Pour the ingredients into an old fashioned glass filled with ice cubes. Stir gently.",
    "image": "https://www.thecocktaildb.com/images/media/drink/2k5gbb1504367689.jpg",
    "ingredients": [
      "Coffee liqueur",
      "Vodka"
    ]
  },
  {
    "cocktailId": "a1118959-bc4b-47a5-bba5-d469f2c49d39",
    "name": "White Russian",
    "glass": "Old-fashioned glass",
    "instructions": "Pour vodka and coffee liqueur over ice cubes in an old-fashioned glass. Fill with light cream and serve.",
    "image": "https://www.thecocktaildb.com/images/media/drink/vsrupw1472405732.jpg",
    "ingredients": [
      "Vodka",
      "Coffee liqueur",
      "Light cream"
    ]
  },
  ...
```

## Testing

Adapt your existing ``MockMVC`` test to use a mocked ``CocktailDBClient``, instruct the mock to return Cocktails that
cause the same Controller response bodies as the previous dummy resources. This means our previous assertions should
still pass successfully.

## Commit and tag your work

Commit your work: use the lab name as comment and tag it with the same name. Don't forget to push to Github.