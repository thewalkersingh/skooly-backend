package com.skooly.backend.exception;
public class SubmissionNotFoundException extends RuntimeException {
   public SubmissionNotFoundException(String message) {
	  super(message);
   }
}