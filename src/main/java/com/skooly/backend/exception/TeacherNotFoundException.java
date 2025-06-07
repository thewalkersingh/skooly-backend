package com.skooly.backend.exception;
public class TeacherNotFoundException extends RuntimeException {
   public TeacherNotFoundException(String message) {
	  super(message);
   }
   
   public TeacherNotFoundException(String message, Throwable cause) {
	  super(message, cause);
   }
}