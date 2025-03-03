package com.rest.springbootemployee.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdInvalidException extends RuntimeException{

    public IdInvalidException() {
        super("Id is invalid");
    }
}
