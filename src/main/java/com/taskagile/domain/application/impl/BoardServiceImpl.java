package com.taskagile.domain.application.impl;

import com.taskagile.domain.application.BoardService;
import com.taskagile.domain.application.commands.CreateBoardCommand;
import com.taskagile.domain.model.board.Board;

import com.taskagile.domain.model.board.BoardManagement;
import com.taskagile.domain.model.board.BoardRepository;
import com.taskagile.domain.model.team.Team;
import com.taskagile.domain.model.team.TeamRepository;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService {

  private final BoardManagement boardManagement;
  private final TeamRepository teamRepository;
  private final UserRepository userRepository;
  private final BoardRepository boardRepository;

  @Transactional
  @Override
  public Board createBoard(CreateBoardCommand command) {

    // 오브젝트 가져오기
    Team team = teamRepository.findById(command.getTeamId()).orElse(null);
    User user = userRepository.findById(command.getUserId()).orElse(null);

    // 보드 생성 및 저장
    Long boardId = boardManagement.createBoard(command.getName(), command.getDescription(), user, team);

    // 이벤트 발행
    return boardRepository.findById(boardId).get();
  }
}
