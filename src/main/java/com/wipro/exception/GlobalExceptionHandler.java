package com.wipro.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {


    	Map<String, String> fieldErrors = ex.getBindingResult()
    			.getFieldErrors()
    			.stream()
    			.collect(Collectors.toMap(
	                fe -> fe.getField(),
	                fe -> fe.getDefaultMessage(),
	                (a, b) -> a,
	                LinkedHashMap::new
	        ));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", 400);
        body.put("error", "Bad Request");
        body.put("path", req.getRequestURI());
        body.put("fieldErrors", fieldErrors);

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleNotFound(EmployeeNotFoundException ex, HttpServletRequest req) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", 404);
        body.put("error", "Not Found");
        body.put("path", req.getRequestURI());
        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex, HttpServletRequest req) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", 500);
        body.put("error", "Internal Server Error");
        body.put("path", req.getRequestURI());
        body.put("message", "Unexpected error");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}

