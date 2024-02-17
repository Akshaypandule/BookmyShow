package com.BookMyShow.exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(){
        super("Please Enter the valid details");
    }
    public ResourceNotFound(String msg){
        super(msg);
    }
}
