package com.ezgroceries.shopinglist.exceptionhandling;

public class EzGroceriesNotFoundException extends RuntimeException {
    public EzGroceriesNotFoundException(String msg) {
        super(msg);
    }
}
