package com.skooly.backend.exception;
public class UserAlreadyExistsException extends RuntimeException {
   public UserAlreadyExistsException(String message) {
	  super(message);
   }
}