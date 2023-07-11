package com.ezgroceries.shopinglist.cocktail;

import com.ezgroceries.shopinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shopinglist.cocktail.persistence.CocktailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AddCocktailListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddCocktailListener.class);
    private final CocktailRepository cocktailRepository;

    public AddCocktailListener(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    @KafkaListener(topics = "quickstart-events", groupId = "${spring.kafka.consumer.group-id}")
    public void foundMissingCocktails(CocktailEntity cocktail) {
        cocktailRepository.save(cocktail);
        LOGGER.debug("cocktail entity saved in database {}", cocktail);
    }
}
