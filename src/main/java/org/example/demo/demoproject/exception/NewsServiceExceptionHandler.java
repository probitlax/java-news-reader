package org.example.demo.demoproject.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class NewsServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoNewsFoundException.class)
    ResponseEntity<Object>  noNewsExceptionHandler(final NoNewsFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("time", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity(body, HttpStatus.ACCEPTED);
    }
}
