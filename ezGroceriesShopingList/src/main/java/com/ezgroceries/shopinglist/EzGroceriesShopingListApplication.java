package com.ezgroceries.shopinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EzGroceriesShopingListApplication {

    public static void main(String[] args) {
        SpringApplication.run(EzGroceriesShopingListApplication.class, args);
    }

}
