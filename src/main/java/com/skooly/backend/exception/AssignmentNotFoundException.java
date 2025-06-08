package com.skooly.backend.exception;
public class AssignmentNotFoundException extends RuntimeException {
   public AssignmentNotFoundException(String message) {
	  super(message);
   }
}