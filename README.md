# KBC cloud native training: practical case

This is a workshop that combines some cloud native theoretical and practical modules. It is focused on a Spring Boot application.

Many thanks to [Nick De Cock](https://github.com/nickdkcronos) who provided the first version of this workshop in 2019. [Patrick Hancke](https://github.com/patrickhancke) reviewed and updated it in 2022 to be in line with the most recent Spring Boot version.

## Prerequisites

Following tools should already be available / installed

* GitHub account
* Java 11
* Postman
* IntelliJ

## Business Case

Our fictional company ``ezGroceries`` wants to provide all sorts of Mobile apps to help people during their grocery shopping.

People can look up cocktails and meals through these apps and add them to a shopping list. The shopping list keeps track of all the distinct ingredients of the cocktails and meals added to the shopping list.

They want to aim at a [minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product): they have identified two APIs to use during this first phase:

* https://www.thecocktaildb.com/api.php
* https://www.themealdb.com/api.php

They plan to combine these two APIs in one of their own. They have a brand new Cloud-Native platform, based on OpenShift that they want to leverage.

## GitHub

Create a free private [github account](https://github.com/) and fork this repository to start implementing the practical case. Add the instructor as a collaborator on your fork.

## Labs

Refer to detailed instructions in each subfolder.

Try to get as far as possible, lab 5 can be considered as the minimal target.

## Tips

Use git to your advantage: branch, make frequent commits, push to github etc..

Refer to the Spring Core course for any practical details regarding Spring.
