package com.ninjarmm.controllers;

import com.ninjarmm.exceptions.ConflictException;
import com.ninjarmm.exceptions.NotFoundException;
import com.ninjarmm.exceptions.UnprocessableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import com.ninjarmm.model.Error;

@RestController
public class BaseController {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> exception(NotFoundException exception){
        Error error = new Error("Resource not found.", exception, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<Object> exception(ConflictException exception){
        Error error = new Error("Already exists.", exception, HttpStatus.CONFLICT);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(value = UnprocessableEntityException.class)
    public ResponseEntity<Object> exception(UnprocessableEntityException exception){
        Error error = new Error("Can not process request", exception, HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(error, error.getStatus());
    }
}
