package com.taskagile.web.apis;

import javax.validation.Valid;

import com.taskagile.domain.application.BoardService;
import com.taskagile.domain.application.commands.CreateBoardCommand;
import com.taskagile.domain.common.security.CurrentUser;
import com.taskagile.domain.model.board.Board;
import com.taskagile.domain.model.user.SimpleUser;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserRepository;
import com.taskagile.web.payloads.CreateBoardPayload;
import com.taskagile.web.results.ApiResult;
import com.taskagile.web.results.CreateBoardResult;

import com.taskagile.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

  private final BoardService boardService;
  private final UserRepository userRepository;

  @PostMapping("/api/boards")
  public ResponseEntity<ApiResult> createBoard(@Valid @RequestBody CreateBoardPayload payload, @CurrentUser SimpleUser currentUser) {

    // TODO: Test
    currentUser = new SimpleUser(userRepository.findByEmailAddress("go1323@gmail.com"));

    // Command 생성
    CreateBoardCommand command = payload.toCommand(currentUser.getUserId());

    // Board 생성
    Board board = boardService.createBoard(command);

    // 리턴
    return CreateBoardResult.build(board);
  }

  @GetMapping("/api/boards/{boardId}")
  public ResponseEntity<ApiResult> getBoard(@PathVariable Long boardId) {
    Board board = boardService.findbyId(boardId);

    if (board == null) {
      return Result.notFound();
    }

    List<User> members = boardService.findMembers(boardId);

    return null;
  }

//  @PostMapping("/api/boards/{boardId}/members")
//  public ResponseEntity<ApiResult> addMember(@PathVariable Long boardId,
//                                             @RequestBody )
}
