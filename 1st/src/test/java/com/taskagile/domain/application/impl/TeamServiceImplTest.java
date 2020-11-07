package com.taskagile.domain.application.impl;

import java.security.InvalidParameterException;
import java.util.Optional;

import com.taskagile.domain.application.commands.CreateTeamCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.model.team.Team;
import com.taskagile.domain.model.team.TeamRepository;
import com.taskagile.domain.model.team.events.TeamCreatedEvent;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TeamServiceImplTest {

  private TeamServiceImpl instance;

  @MockBean
  private UserRepository userRepositoryMock;
  @MockBean
  private TeamRepository teamRepositoryMock;
  @MockBean
  private DomainEventPublisher domainEventPublisherMock;

  @Before
  public void setup() {
    instance = new TeamServiceImpl(userRepositoryMock, teamRepositoryMock, domainEventPublisherMock);
  }

  @Test (expected = Exception.class)
  public void createTeam_nullCommand_shouldFail() throws Exception {
    instance.createTeam(null);
  }

  @Test (expected = InvalidParameterException.class)
  public void createTeam_emptyCommandValue_shouldFail() throws Exception {

    // given
    CreateTeamCommand command = new CreateTeamCommand(null, "");

    // when
    instance.createTeam(command);
  }

  @Test
  public void createTeam_validCommand_shouldSucceed() throws Exception {

    // given
    Long userId = 1L;
    String username = "testUser";
    String emailAddress = "test@test.com";
    String password = "testPassword";

    String teamName = "testTeam";

    User user = User.create(username, emailAddress, password);

    Mockito.when(userRepositoryMock.findById(userId))
      .thenReturn(Optional.of(user));

    Team team = Team.create(teamName, user);

    Mockito.when(teamRepositoryMock.save(team))
      .thenReturn(team);

    CreateTeamCommand commnad = new CreateTeamCommand(userId, teamName);

    // when
    instance.createTeam(commnad);


    // then
    Mockito.verify(domainEventPublisherMock)
      .publish(new TeamCreatedEvent(instance, team));
  }
}
