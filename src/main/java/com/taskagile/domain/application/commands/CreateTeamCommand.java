package com.taskagile.domain.application.commands;

public class CreateTeamCommand {

  private Long userId;
  private String name;

  public CreateTeamCommand(Long userId, String name) {
    this.userId = userId;
    this.name = name;
  }
}
