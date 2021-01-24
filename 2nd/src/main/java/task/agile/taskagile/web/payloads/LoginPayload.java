package task.agile.taskagile.web.payloads;

import lombok.Getter;
import lombok.Setter;
import task.agile.taskagile.domain.application.commands.LoginCommand;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginPayload {

  @NotNull
  private String username;
  @NotNull
  private String password;

  public LoginCommand toCommand() {
    return new LoginCommand(username, password);
  }
}
