package com.ezgroceries.shopinglist.exceptionhandling;

public class EzGroceriesBadRequestException extends RuntimeException {
    public EzGroceriesBadRequestException(String msg) {
        super(msg);
    }
}
