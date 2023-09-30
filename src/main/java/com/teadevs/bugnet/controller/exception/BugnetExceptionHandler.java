package com.teadevs.bugnet.controller.exception;

import com.teadevs.bugnet.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BugnetExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ResponseBody
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAllExceptions(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, e.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
}