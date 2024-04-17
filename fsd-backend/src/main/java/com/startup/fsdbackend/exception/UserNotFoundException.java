package com.startup.fsdbackend.exception;

public class UserNotFoundException extends RuntimeException{

public UserNotFoundException(Long id){
    super("The requested order id "+id+" is not available");
}

}
