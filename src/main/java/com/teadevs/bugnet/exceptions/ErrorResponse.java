package com.teadevs.bugnet.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    
    private String message;
    
    private HttpStatus httpStatus;
    
    private String type;
    
    public ErrorResponse(String message, HttpStatus httpStatus, String type) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.type = type;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
}