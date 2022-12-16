package com.school.school.errors;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(MyCustomExceptions.class)
  public Map<String, Object> handleMyCustomException(MyCustomExceptions ex) {
    Map<String, Object> response = new HashMap<>();

    response.put("error", ex.getReasonPhrase());
    response.put("message", ex.getMessage());
    response.put("statusCode", ex.getStatusCode());
    return response;
  }

}