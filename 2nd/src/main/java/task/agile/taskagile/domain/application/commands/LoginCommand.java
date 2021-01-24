package task.agile.taskagile.domain.application.commands;

import lombok.Getter;

@Getter
public class LoginCommand {
  private String username;
  private String password;

  public LoginCommand(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
