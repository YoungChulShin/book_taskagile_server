package task.agile.taskagile.domain.application;

import task.agile.taskagile.domain.application.commands.CreateBoardCommand;
import task.agile.taskagile.domain.model.board.Board;

import java.util.List;

public interface BoardService {

  Board createBoard(CreateBoardCommand command);

  List<Board> findBoardsByMembership(long userId);
}
