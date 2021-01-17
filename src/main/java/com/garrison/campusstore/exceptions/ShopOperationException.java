package com.garrison.campusstore.exceptions;

public class ShopOperationException extends RuntimeException{
    private static final long serialVersionUID = 2834532426243920854L;

    public ShopOperationException(String msg){
        super(msg);
    }
}
