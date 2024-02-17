package com.BookMyShow.exception;

public class DataNotFoundException extends RuntimeException{

    private String resourceName;
    private String filedName;

    private String filedValue;


    public DataNotFoundException(String resourceName,String filedName,String filedValue){
        super(String.format("%s not found with %s : %s",resourceName,filedName,filedValue));

        this.filedName=filedName;
        this.resourceName=resourceName;
        this.filedValue=filedValue;
    }
}
