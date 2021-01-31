package task.agile.taskagile.domain.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.agile.taskagile.domain.application.BoardService;
import task.agile.taskagile.domain.application.commands.CreateBoardCommand;
import task.agile.taskagile.domain.model.board.Board;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

  @Override
  public Board createBoard(CreateBoardCommand command) {
    return null;
  }

  @Override
  public List<Board> findBoardsByMembership(long userId) {
    return null;
  }
}
