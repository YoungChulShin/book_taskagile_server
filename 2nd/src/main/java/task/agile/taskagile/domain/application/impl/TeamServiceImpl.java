package task.agile.taskagile.domain.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.agile.taskagile.domain.application.TeamService;
import task.agile.taskagile.domain.application.commands.CreateTeamCommand;
import task.agile.taskagile.domain.common.event.DomainEventPublisher;
import task.agile.taskagile.domain.model.team.Team;
import task.agile.taskagile.domain.model.team.TeamRepository;
import task.agile.taskagile.domain.model.team.events.TeamCreatedEvent;
import task.agile.taskagile.domain.model.user.User;
import task.agile.taskagile.domain.model.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

  private final UserRepository userRepository;
  private final TeamRepository teamRepository;
  private final DomainEventPublisher domainEventPublisher;

  @Override
  @Transactional
  public Team createTeam(CreateTeamCommand command) {
    Optional<User> findUser = userRepository.findById(command.getUserId());
    if (!findUser.isPresent()) {
      return null;
    }

    Team team = Team.create(command.getName(), findUser.get());
    teamRepository.save(team);

    domainEventPublisher.publish(new TeamCreatedEvent(team));

    return team;
  }

  @Override
  public List<Team> findTeamsByUserId(long userId) {
    Optional<User> findUser = userRepository.findById(userId);
    if (!findUser.isPresent()) {
      return null;
    }

    return findUser.get().getTeams();
  }
}
