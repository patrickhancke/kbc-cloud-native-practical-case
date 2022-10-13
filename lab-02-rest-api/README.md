# Lab 02 - REST API
In close collaboration with the Mobile teams, our ``ezGroceries`` team has designed a REST API that will cover all the functionalities of the initial minimum viable product.

To make sure the Mobile app can start their implementation asap we have decided to implement these APIs using dummy values before we integrate with the 3rd party cocktail and meal APIs.

Other technical decisions that were taken during the design include:

* Only JSON representation will be supported
* OpenAPI docs, provided by [Swagger](https://swagger.io/specification/), will be provided alongside the API for contract documentation and interactive explorability
* UUID's will be used to uniquely identify every resource

# Endpoint for searching cocktails
These are the requirements for the endpoint:
* HTTP GET ``/cocktails``
* it must support a query parameter named ``search`` which accepts a String value. This parameter acts as a filter to restrict the number of returned cocktails
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
private static final Logger log = LoggerFactory.getLogger(MyClass.class);
```

Verification: navigate to http://localhost:8080/cocktails?search=Russian and compare the response to the above sample. It must match exactly.
