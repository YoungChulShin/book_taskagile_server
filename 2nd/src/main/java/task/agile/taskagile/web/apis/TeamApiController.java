package task.agile.taskagile.web.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import task.agile.taskagile.domain.application.TeamService;
import task.agile.taskagile.domain.common.security.CurrentUser;
import task.agile.taskagile.domain.model.team.Team;
import task.agile.taskagile.domain.model.user.SimpleUser;
import task.agile.taskagile.web.payloads.CreateTeamPayload;
import task.agile.taskagile.web.results.ApiResult;
import task.agile.taskagile.web.results.CreateTeamResult;

@Controller
@RequiredArgsConstructor
public class TeamApiController {

  private final TeamService teamService;

  @PostMapping("/api/teams")
  public ResponseEntity<ApiResult> createTeam(@RequestBody CreateTeamPayload createTeamPayload,
                                              @CurrentUser SimpleUser currentUser) {

    Team team = teamService.createTeam(createTeamPayload.toCommand(currentUser.getUserId()));
    return CreateTeamResult.build(team);
  }
}
