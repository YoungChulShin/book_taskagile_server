package com.taskagile.domain.application.commands;

import lombok.Getter;

@Getter
public class CreateBoardCommand {

  private String name;
  private String description;
  private Long userId;
  private Long teamId;

  public CreateBoardCommand(String name, String description, Long userId, Long teamId) {
    this.name = name;
    this.description = description;
    this.userId = userId;
    this.teamId = teamId;
  }
}
