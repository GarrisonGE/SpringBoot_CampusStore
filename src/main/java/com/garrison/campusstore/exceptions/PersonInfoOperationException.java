package com.garrison.campusstore.exceptions;

public class PersonInfoOperationException extends RuntimeException{
    private static final long serialVersionUID = 8472927240159091336L;
    public PersonInfoOperationException(String msg){
        super(msg);
    }

}
