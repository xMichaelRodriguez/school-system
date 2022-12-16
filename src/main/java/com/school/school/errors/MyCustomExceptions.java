package com.school.school.errors;

public class MyCustomExceptions extends Exception {

  private String message;
  private int statusCode;
  private String reasonPhrase;

  public MyCustomExceptions(String message, int statusCode, String reasonPhrase) {
    super(message);
    this.message = message;
    this.statusCode = statusCode;
    this.reasonPhrase = reasonPhrase;
  }

  public String getMessage() {
    return this.message;
  }

  public int getStatusCode() {
    return this.statusCode;
  }

  public String getReasonPhrase() {
    return this.reasonPhrase;

  }
}
