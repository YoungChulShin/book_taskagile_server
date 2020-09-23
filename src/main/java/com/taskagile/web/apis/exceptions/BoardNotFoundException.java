package com.taskagile.web.apis.exceptions;

public class BoardNotFoundException extends RuntimeException {

  public BoardNotFoundException() {
  }

  public BoardNotFoundException(String message) {
    super(message);
  }
}
