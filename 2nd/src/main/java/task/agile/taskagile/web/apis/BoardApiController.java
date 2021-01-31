package task.agile.taskagile.web.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import task.agile.taskagile.domain.application.BoardService;
import task.agile.taskagile.domain.common.security.CurrentUser;
import task.agile.taskagile.domain.model.board.Board;
import task.agile.taskagile.domain.model.user.SimpleUser;
import task.agile.taskagile.web.payloads.CreateBoardPayload;
import task.agile.taskagile.web.results.ApiResult;
import task.agile.taskagile.web.results.CreateBoardResult;

@Controller
@RequiredArgsConstructor
public class BoardApiController {

  private BoardService boardService;

  @PostMapping("/api/boards")
  public ResponseEntity<ApiResult> createBoard(@RequestBody CreateBoardPayload payload,
                                               @CurrentUser SimpleUser currentUser) {
    Board board = boardService.createBoard(payload.toCommand(currentUser.getUserId()));
    return CreateBoardResult.build(board);
  }
}
