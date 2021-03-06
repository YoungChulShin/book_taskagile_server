package task.agile.taskagile.web.results;

import org.springframework.http.ResponseEntity;
import task.agile.taskagile.domain.model.board.Board;

public class CreateBoardResult {

  public static ResponseEntity<ApiResult> build(Board board) {
    ApiResult apiResult = ApiResult.blank()
      .add("id", board.getId().toString())
      .add("name", board.getName())
      .add("description", board.getDescription())
      .add("teamId", board.getTeam().getId().toString());

    return ResponseEntity.ok(apiResult);
  }
}
