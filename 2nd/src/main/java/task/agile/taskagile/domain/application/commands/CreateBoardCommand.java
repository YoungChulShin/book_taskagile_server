package task.agile.taskagile.domain.application.commands;

import lombok.Getter;

@Getter
public class CreateBoardCommand {

  private String name;
  private String description;
  private long userId;
  private long teamId;

  public CreateBoardCommand(long userId, String name, String description, long teamId) {
    this.userId = userId;
    this.name = name;
    this.description = description;
    this.teamId = teamId;
  }
}
