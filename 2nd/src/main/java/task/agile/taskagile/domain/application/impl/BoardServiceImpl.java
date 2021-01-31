package task.agile.taskagile.domain.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.agile.taskagile.domain.application.BoardService;
import task.agile.taskagile.domain.application.commands.CreateBoardCommand;
import task.agile.taskagile.domain.common.event.DomainEventPublisher;
import task.agile.taskagile.domain.model.board.Board;
import task.agile.taskagile.domain.model.board.BoardManagement;
import task.agile.taskagile.domain.model.board.events.BoardCreatedEvent;
import task.agile.taskagile.domain.model.team.Team;
import task.agile.taskagile.domain.model.team.TeamRepository;
import task.agile.taskagile.domain.model.user.User;
import task.agile.taskagile.domain.model.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final TeamRepository teamRepository;
  private final UserRepository userRepository;
  private final BoardManagement boardManagement;
  private final DomainEventPublisher domainEventPublisher;

  @Override
  @Transactional
  public Board createBoard(CreateBoardCommand command) {
    Optional<User> findUser = userRepository.findById(command.getUserId());
    if (!findUser.isPresent()) {
      return null;
    }

    Optional<Team> findTeam = teamRepository.findById(command.getTeamId());
    if (!findTeam.isPresent()) {
      return null;
    }

    Board board = boardManagement.createBoard(
      findUser.get(), command.getName(), command.getDescription(), findTeam.get());

    domainEventPublisher.publish(new BoardCreatedEvent(board));

    return board;
  }

  @Override
  public List<Board> findBoardsByMembership(long userId) {
    Optional<User> findUser = userRepository.findById(userId);
    if (!findUser.isPresent()) {
      return null;
    }

    return findUser.get().getBoards();
  }
}
