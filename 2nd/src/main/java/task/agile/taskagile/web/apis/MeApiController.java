package task.agile.taskagile.web.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import task.agile.taskagile.domain.application.BoardService;
import task.agile.taskagile.domain.application.TeamService;
import task.agile.taskagile.domain.common.security.CurrentUser;
import task.agile.taskagile.domain.model.board.Board;
import task.agile.taskagile.domain.model.team.Team;
import task.agile.taskagile.domain.model.user.SimpleUser;
import task.agile.taskagile.web.results.ApiResult;
import task.agile.taskagile.web.results.MyDataResult;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MeApiController {

  private final TeamService teamService;
  private final BoardService boardService;

  @GetMapping("/api/me")
  public ResponseEntity<ApiResult> getMyData(@CurrentUser SimpleUser currentUser) {
    List<Team> teams = teamService.findTeamsByUserId(currentUser.getUserId());
    List<Board> boards = boardService.findBoardsByMembership(currentUser.getUserId());

    return MyDataResult.build(currentUser, teams, boards);

  }
}
