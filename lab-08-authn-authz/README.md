# (Optional) Lab 08 - Authentication and authorization

An optional lab for those with time to spare! This only includes basic requirements and a suggested way of working.

We want to add extra functionality to our shopping list, they should be user specific. This has quite an impact on our
application:

Basic requirements:

* Use Spring Security (add appropriate dependency)
* Only authenticated users are allowed to de shopping list API calls
* Non-authenticated users are still allowed to search cocktails
* Shopping lists need to contain a user identification attribute
* The only shopping lists a user can see are the ones he created himself

## Authentication

Suggested way of working:

* Provide users through Spring
  Security's [JDBC authentication support](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/jdbc.html)
* Make sure to create the JDBC user's tables through ``Flyway`` scripts
* Users need to be pre-populated on application start-up somehow, no sign-up functionality needed
* Provide configuration to authenticate the given URL paths (see Basic requirements)

## Authorization

* Authorize shopping list API calls
* Check the ``userId`` of the authenticated user and the ``userId`` of the specific shopping list (tips: use ``SpeL``
  and ``@PreAuthorize`` annotation)

## Commit and tag your work

Commit your work: use the lab name as comment and tag it with the same name. Don't forget to push to Github.