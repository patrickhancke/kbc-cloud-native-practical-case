package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
//@EntityScan("com.ezgroceries.shoppinglist")
public class EzGroceriesShoppingListApplication {

    public static void main(String[] args) {
        SpringApplication.run(EzGroceriesShoppingListApplication.class, args);
    }

}
