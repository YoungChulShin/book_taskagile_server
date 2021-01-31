package task.agile.taskagile.domain.application.impl;

import org.springframework.stereotype.Service;
import task.agile.taskagile.domain.application.TeamService;
import task.agile.taskagile.domain.application.commands.CreateTeamCommand;
import task.agile.taskagile.domain.model.team.Team;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
  @Override
  public Team createTeam(CreateTeamCommand command) {
    return null;
  }

  @Override
  public List<Team> findTeamsByUserId(long userId) {
    return null;
  }
}
