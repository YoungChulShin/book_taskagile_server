package com.taskagile.domain.application.impl;

import com.taskagile.domain.application.BoardService;
import com.taskagile.domain.application.commands.CreateBoardCommand;
import com.taskagile.domain.model.board.Board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService {

  @Override
  public Board createBoard(CreateBoardCommand command) {

    // 오브젝트 가져오기

    // 보드 생성

    // 저장

    // 이벤트 발행
    return null;
  }
}
