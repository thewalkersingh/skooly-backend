package com.skooly.backend.exception;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<Map<String, Object>> handleEntityNotFound(EntityNotFoundException ex) {
	  Map<String, Object> error = new HashMap<>();
	  error.put("timestamp", LocalDateTime.now());
	  error.put("status", HttpStatus.NOT_FOUND.value());
	  error.put("error", "Not Found");
	  error.put("message", ex.getMessage());
	  return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
	  Map<String, Object> error = new HashMap<>();
	  error.put("timestamp", LocalDateTime.now());
	  error.put("status", HttpStatus.BAD_REQUEST.value());
	  error.put("error", "Validation Error");
	  error.put("message", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
	  return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
   }
   
   @ExceptionHandler(Exception.class)
   public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
	  Map<String, Object> error = new HashMap<>();
	  error.put("timestamp", LocalDateTime.now());
	  error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	  error.put("error", "Server Error");
	  error.put("message", ex.getMessage());
	  return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
   }
}