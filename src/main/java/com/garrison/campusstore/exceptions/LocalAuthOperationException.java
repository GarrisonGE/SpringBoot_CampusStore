package com.garrison.campusstore.exceptions;

public class LocalAuthOperationException extends RuntimeException{

    private static final long serialVersionUID = -4395402100294410581L;
    public LocalAuthOperationException(String msg){
        super(msg);
    }
}
