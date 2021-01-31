package task.agile.taskagile.web.payloads;

import lombok.Getter;
import task.agile.taskagile.domain.application.commands.CreateTeamCommand;

@Getter
public class CreateTeamPayload {

  private String name;

  public CreateTeamCommand toCommand(long userId) {
    return new CreateTeamCommand(userId, name);
  }
}
