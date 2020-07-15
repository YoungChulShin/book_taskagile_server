package com.taskagile.web.payloads;

import com.taskagile.domain.application.commands.RegistrationCommand;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
public class RegistrationPayload {

  @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
  @NotNull
  private String username;

  @Email(message = "Email address should be valid")
  @Size(max = 100)
  @NotNull
  private String emailAddress;

  @Size(min = 6, max = 30)
  @NotNull
  private String password;

  public RegistrationCommand toCommand()
  {
    return new RegistrationCommand(username, emailAddress, password);
  }
}
