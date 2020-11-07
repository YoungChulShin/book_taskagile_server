package com.taskagile.domain.application.impl;

import java.security.InvalidParameterException;

import com.taskagile.domain.application.TeamService;
import com.taskagile.domain.application.commands.CreateTeamCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.model.team.Team;
import com.taskagile.domain.model.team.TeamRepository;
import com.taskagile.domain.model.team.events.TeamCreatedEvent;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserRepository;
import com.taskagile.domain.model.user.exceptions.UserNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TeamServiceImpl implements TeamService {

  private final UserRepository userRepository;
  private final TeamRepository teamRepository;
  private final DomainEventPublisher domainEventPublisher;

  @Transactional
  @Override
  public Team createTeam(CreateTeamCommand command) throws UserNotFoundException {

    // 입력 값이 공백인지 체크
    Assert.notNull(command, "Parameter 'command' must not be null");

    if (command.getUserId() == null || StringUtils.isEmpty(command.getName())) {
      throw new InvalidParameterException("Parameter 'command' value must not be null or emtpy");
    }

    // 저장
    User user = userRepository.findById(command.getUserId())
                              .orElseThrow(() -> new UserNotFoundException("Can't find user"));

    Team team = Team.create(command.getName(), user);
    teamRepository.save(team);

    // 이벤트 발행
    domainEventPublisher.publish(new TeamCreatedEvent(this, team));

    return team;
  }
}
