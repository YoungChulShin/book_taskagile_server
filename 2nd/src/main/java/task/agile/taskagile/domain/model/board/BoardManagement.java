package task.agile.taskagile.domain.model.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import task.agile.taskagile.domain.model.team.Team;
import task.agile.taskagile.domain.model.user.User;

@Component
@RequiredArgsConstructor
public class BoardManagement {

  private final BoardRepository boardRepository;
  private final BoardMemberRepository boardMemberRepository;

  public Board createBoard(User user, String name, String description, Team team) {

    Board board = Board.create(name, description, user, team);
    boardRepository.save(board);

    BoardMember boardMember = BoardMember.create(board, user);
    boardMemberRepository.save(boardMember);

    return board;
  }
}
