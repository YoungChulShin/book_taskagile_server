package com.taskagile.web.apis;

import javax.validation.Valid;

import com.taskagile.domain.application.TeamService;
import com.taskagile.domain.application.UserService;
import com.taskagile.domain.common.security.CurrentUser;
import com.taskagile.domain.model.team.Team;
import com.taskagile.domain.model.user.SimpleUser;
import com.taskagile.web.payloads.CreateTeamPayload;
import com.taskagile.web.results.ApiResult;
import com.taskagile.web.results.CreateTeamResult;
import com.taskagile.web.results.Result;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TeamApiController {

  private final TeamService teamService;
  private final UserService userService;

  @PostMapping("/api/teams")
  public ResponseEntity<ApiResult> createTeam(@Valid @RequestBody CreateTeamPayload payload, @CurrentUser SimpleUser currentUser) {

    try {
      Team team = teamService.createTeam(payload.toCommand(currentUser.getUserId()));
      return CreateTeamResult.build(team);
    } catch (Exception e) {
      return Result.failure("Not found user");
    }
  }
}
