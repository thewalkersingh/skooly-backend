package com.skooly.backend.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(SchoolNotFoundException.class)
   public ResponseEntity<?> handleSchoolNotFoundException(SchoolNotFoundException ex) {
	  // You can customize the error response as needed (e.g., using a custom error response DTO)
	  return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
   }
   
   // Add more exception handlers for different custom exceptions or generic handlers
   @ExceptionHandler(Exception.class)
   public ResponseEntity<?> handleGenericException(Exception ex) {
	  return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   @ExceptionHandler(StudentNotFoundException.class)
   public ResponseEntity<?> handleStudentNotFoundException(StudentNotFoundException ex) {
	  return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
   }
}