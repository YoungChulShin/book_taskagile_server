package task.agile.taskagile.web.results;

import org.springframework.http.ResponseEntity;
import task.agile.taskagile.domain.model.team.Team;

public class CreateTeamResult {

  public static ResponseEntity<ApiResult> build(Team team) {
    ApiResult apiResult = ApiResult.blank()
      .add("id", team.getId().toString())
      .add("name", team.getName());

    return ResponseEntity.ok(apiResult);
  }
}
