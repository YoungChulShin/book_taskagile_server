package com.taskagile.web.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.taskagile.domain.application.commands.CreateTeamCommand;

import lombok.Data;

@Data
public class CreateTeamPayload {

  @NotEmpty
  private String name;

  public CreateTeamCommand toCommand(Long userId) {
    return new CreateTeamCommand(userId, name);
  }
}
