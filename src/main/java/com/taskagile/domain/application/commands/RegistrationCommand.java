package com.taskagile.domain.application.commands;

import lombok.Getter;

@Getter
public class RegistrationCommand {

  private String username;
  private String emailAddress;
  private String password;

  public RegistrationCommand(String username, String emailAddress, String password) {
    this.username = username;
    this.emailAddress = emailAddress;
    this.password = password;
  }
}
