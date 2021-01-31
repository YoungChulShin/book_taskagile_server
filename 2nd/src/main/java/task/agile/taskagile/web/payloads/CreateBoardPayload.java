package task.agile.taskagile.web.payloads;

import lombok.Getter;
import task.agile.taskagile.domain.application.commands.CreateBoardCommand;

@Getter
public class CreateBoardPayload {

  private String name;
  private String description;
  private long teamId;

  public CreateBoardCommand toCommand(long userId) {
    return new CreateBoardCommand(userId, name, description, teamId);
  }
}
