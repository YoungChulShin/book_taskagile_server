package com.taskagile.web.apis;

import javax.validation.Valid;

import com.taskagile.domain.application.TeamService;
import com.taskagile.domain.common.security.CurrentUser;
import com.taskagile.domain.model.team.Team;
import com.taskagile.domain.model.user.SimpleUser;
import com.taskagile.web.payloads.CreateTeamPayload;
import com.taskagile.web.results.ApiResult;
import com.taskagile.web.results.Result;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TeamApiController {

  private final TeamService teamService;

  @PostMapping("/api/teams")
  public ResponseEntity<ApiResult> createTeam(@Valid @RequestBody CreateTeamPayload payload,
                                              @CurrentUser SimpleUser currentUser) {

      Team team = teamService.createTeam(payload.toCommand(currentUser.getUserId()));
        // 서비스를 통해서 팀 생성 호출
        // 결과 리턴

      return CreateTeamResult.build(team);
  }

  @Data
  private static class CreateTeamResult {
    private static ResponseEntity<ApiResult> build(Team team) {
      ApiResult apiResult = ApiResult.blank().add("id", team.getId().toString())
        .add("name", team.getName());

        return Result.ok(apiResult);
    }
  }
}
