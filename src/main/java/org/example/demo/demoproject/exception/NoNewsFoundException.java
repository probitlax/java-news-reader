package org.example.demo.demoproject.exception;

public class NoNewsFoundException extends RuntimeException  {
    public NoNewsFoundException(String message) {
         super(message);
    }
}
