package com.backend.estudiantes.utils;

import com.backend.estudiantes.model.Usuario;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponseBuilder {

    public static Map<String, Object> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("Error", message);
        response.put("Status", status.value());
        response.put("times", System.currentTimeMillis());
        return response;
    }
}
