package task.agile.taskagile.domain.application.commands;

import lombok.Getter;

@Getter
public class CreateTeamCommand {

  private long userId;
  private String name;

  public CreateTeamCommand(long userId, String name) {
    this.userId = userId;
    this.name = name;
  }
}
