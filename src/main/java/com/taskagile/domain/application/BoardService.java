package com.taskagile.domain.application;

import com.taskagile.domain.application.commands.CreateBoardCommand;
import com.taskagile.domain.model.board.Board;

public interface BoardService {

  Board createBoard(CreateBoardCommand command);
}
