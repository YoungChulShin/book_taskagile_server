package task.agile.taskagile.web.results;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import task.agile.taskagile.domain.model.board.Board;
import task.agile.taskagile.domain.model.team.Team;
import task.agile.taskagile.domain.model.user.SimpleUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDataResult {

  public static ResponseEntity<ApiResult> build(SimpleUser currentUser, List<Team> teams, List<Board> boards) {

    Map<String, Object> user = new HashMap<>();
    user.put("name", currentUser.getUsername());

    List<TeamResult> teamResults = new ArrayList<>();
    teams.stream().forEach(t -> teamResults.add(new TeamResult(t)));

    List<BoardResult> boardResults = new ArrayList<>();
    boards.stream().forEach(b -> boardResults.add(new BoardResult(b)));

    ApiResult apiResult = ApiResult.blank()
      .add("user", user)
      .add("teams", teamResults)
      .add("boards", boardResults);

    return ResponseEntity.ok(apiResult);
  }

  @Getter
  private static class TeamResult {
    private long id;
    private String name;

    TeamResult(Team team) {
      this.id = team.getId();
      this.name = team.getName();
    }
  }

  @Getter
  private static class BoardResult {
    private long id;
    private String name;
    private String description;
    private long teamId;

    public BoardResult(Board board) {
      this.id = board.getId();
      this.name = board.getName();
      this.description = board.getDescription();
      this.teamId = board.getTeam().getId();
    }
  }
}
