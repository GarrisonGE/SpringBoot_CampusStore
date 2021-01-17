package com.garrison.campusstore.exceptions;

public class ProductCategoryOperationException extends RuntimeException{

    private static final long serialVersionUID = 9194152962260752471L;

    public ProductCategoryOperationException(String msg){
        super(msg);
    }
}
