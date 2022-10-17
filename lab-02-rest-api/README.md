# Lab 02 - REST API
In close collaboration with the Mobile teams, our ``ezGroceries`` team has designed a REST API that will cover all the functionalities of the initial minimum viable product.

To make sure the Mobile app can start their implementation asap we have decided to implement these APIs using dummy values before we integrate with the 3rd party cocktail and meal APIs.

Other technical decisions that were taken during the design include:

* Only JSON representation will be supported
* OpenAPI docs, provided by [Swagger](https://swagger.io/specification/), will be provided alongside the API for contract documentation and interactive explorability
* UUID's will be used to uniquely identify every resource, use the ``java.util.UUID`` type.

# Cocktail API
These are the requirements for the endpoint:
* HTTP GET ``/cocktails``
* it must support a query parameter named ``search`` which accepts a ``String`` value. This parameter acts as a filter to restrict the number of returned cocktails
* it must return the cocktail resource(s) in ``json`` format

Sample request: ``GET http://localhost:8080/cocktails?search=Russian``

Return code: ``200 OK``

Sample response:
```json
[
    {
        "cocktailId": "23b3d85a-3928-41c0-a533-6538a71e17c4",
        "name": "Margerita",
        "glass": "Cocktail glass",
        "instructions": "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
        "image": "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
        "ingredients": [
            "Tequila",
            "Triple sec",
            "Lime juice",
            "Salt"
        ]
    },
    {
        "cocktailId": "d615ec78-fe93-467b-8d26-5d26d8eab073",
        "name": "Blue Margerita",
        "glass": "Cocktail glass",
        "instructions": "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
        "image": "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
        "ingredients": [
            "Tequila",
            "Blue Curacao",
            "Lime juice",
            "Salt"
        ]
    }
]
```

Guidelines for getting started:
* create a Spring REST controller and map it to the correct endpoint
* create a method to return the list of cocktails, map it to the ``GET`` HTTP method. Don't forget to declare the ``search`` request parameter.
* hard-code the 2 cocktail resources from the above sample and return them from the method you just declared
* add logging to your method by declaring an ``org.slf4j.Logger`` in your controller:
```java
private static final Logger log = LoggerFactory.getLogger(MyController.class);
```
or use [Project Lombok](https://projectlombok.org/) if you prefer.

Verification: navigate to http://localhost:8080/cocktails?search=Russian and compare the response to the above sample. It must match exactly.

# Shopping List API
## Create a new Shopping List
Request: ``POST http://localhost:8080/shopping-lists``

Request body (json):
```json
{
  "name": "Stephanie's birthday"
}
```

Response status: ``201 CREATED``

Response body: empty

**NOTE**: the response headers must contain the ``Location`` header and point to the newly created resource.

Verification: use ``Postman`` to create a new shopping list.

## Add Cocktail to Shopping List

This API call links a previously created ``Cocktail`` resource to the ``ShoppingList`` resource.

Request: ``POST http://localhost:8080/shopping-lists/{shoppingListId}/cocktails``

Request body (json):

```json
{
  "cocktailId": "23b3d85a-3928-41c0-a533-6538a71e17c4"
}
```

Response status: ``201 CREATED``

Response body: empty

**NOTE**: the response headers must contain the ``Location`` header and point to the newly created resource.

## Get Shopping List

This method will provide the main (currently still very simple) functionality of our API, it will return a distinct set
of ingredients derived from all the cocktails that have been added to this shopping list.

**NOTE**: we currently only return some dummy generated data, just focus on getting the contract right.

Request: ``GET http://localhost:8080/shopping-lists/{shoppingListId}``

Response status: ``200 OK``

Response body:

```json
{
  "shoppingListId": "90689338-499a-4c49-af90-f1e73068ad4f",
  "name": "Stephanie's birthday",
  "ingredients": [
    "Tequila",
    "Triple sec",
    "Lime juice",
    "Salt",
    "Blue Curacao"
  ]
}
```

## Get all Shopping Lists

Request: ``GET http://localhost:8080/shopping-lists``

Response status: ``200 OK``

Response body:

```json
[
  {
    "shoppingListId": "4ba92a46-1d1b-4e52-8e38-13cd56c7224c",
    "name": "Stephanie's birthday",
    "ingredients": [
      "Tequila",
      "Triple sec",
      "Lime juice",
      "Salt",
      "Blue Curacao"
    ]
  },
  {
    "shoppingListId": "6c7d09c2-8a25-4d54-a979-25ae779d2465",
    "name": "My Birthday",
    "ingredients": [
      "Tequila",
      "Triple sec",
      "Lime juice",
      "Salt",
      "Blue Curacao"
    ]
  }
]
```

# Testing

Add Spring MockMVC tests for all the API calls we just implemented. At the minimum, assert the following for each of
them:

* Response status
* Content type
* JSON response body attributes

# API docs

To document and to be able to interactively test our API we're going to add Open API (Swagger) documentation.

Follow the [instructions for Spring Doc](https://springdoc.org/) to set it up. The only thing you need to do is include
the ``org.springdoc.springdoc-openapi-ui`` dependency.

Verification: open the [Swagger home page](http://localhost:8080/swagger-ui/index.html) in your browser. It shows a list
of Controllers and the API methods you implemented.

# Commit and tag your work

Make sure to add and commit all your files at least once at the end of every lab. After the lab has been completed,
please tag it with the appropriate lab number:

``git tag -a lab02 -m "lab02"``
