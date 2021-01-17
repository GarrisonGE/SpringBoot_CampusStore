package com.garrison.campusstore.exceptions;

public class ShopCategoryOperationException extends RuntimeException{
    private static final long serialVersionUID = -8897446256929097887L;
    public ShopCategoryOperationException(String msg){
        super(msg);
    }
}
