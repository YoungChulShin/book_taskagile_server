package com.taskagile.domain.model.user.exceptions;

public class UserNotFoundException extends Exception {

  private static final long serialVersionUID = 4395765021869681362L;

  public UserNotFoundException(String message) {
    super(message);
  }
}
