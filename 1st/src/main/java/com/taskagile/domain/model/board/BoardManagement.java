package com.taskagile.domain.model.board;

import com.taskagile.domain.model.team.Team;
import com.taskagile.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BoardManagement {

  private final BoardRepository boardRepository;

  public Long createBoard(String name, String description, User user, Team team) {

    Board board = Board.create(name, description, user, team);
    boardRepository.save(board);

    // Board Member 처리

    return board.getId();
  }
}
