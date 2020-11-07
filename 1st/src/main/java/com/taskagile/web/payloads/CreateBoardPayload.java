package com.taskagile.web.payloads;

import javax.validation.constraints.NotEmpty;

import com.taskagile.domain.application.commands.CreateBoardCommand;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBoardPayload {

  @NotEmpty
  private String name;
  private String description;
  private Long teamId;

  public CreateBoardCommand toCommand(Long userId) {
    return new CreateBoardCommand(name, description, userId, teamId);
  }
}
