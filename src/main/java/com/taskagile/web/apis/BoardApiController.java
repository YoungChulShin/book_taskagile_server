package com.taskagile.web.apis;

import javax.validation.Valid;

import com.taskagile.domain.application.BoardService;
import com.taskagile.domain.application.commands.CreateBoardCommand;
import com.taskagile.domain.common.security.CurrentUser;
import com.taskagile.domain.model.board.Board;
import com.taskagile.domain.model.user.SimpleUser;
import com.taskagile.domain.model.user.UserRepository;
import com.taskagile.web.payloads.CreateBoardPayload;
import com.taskagile.web.results.ApiResult;
import com.taskagile.web.results.CreateBoardResult;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

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
}
