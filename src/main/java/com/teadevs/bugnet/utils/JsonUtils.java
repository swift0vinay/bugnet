package com.teadevs.bugnet.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teadevs.bugnet.exceptions.UtilsException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class JsonUtils {
    
    public <T> T readJson(String file, Class<T> type) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(file), type);
        } catch (IOException e) {
            throw new UtilsException("Failed to read json");
        }
        
    }
    
    public String toJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new UtilsException("Failed to write json");
        }
    }
    
}