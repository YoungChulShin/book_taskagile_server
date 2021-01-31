package task.agile.taskagile.domain.application;

import task.agile.taskagile.domain.application.commands.CreateTeamCommand;
import task.agile.taskagile.domain.model.team.Team;

import java.util.List;

public interface TeamService {

  Team createTeam(CreateTeamCommand command);

  List<Team> findTeamsByUserId(long userId);
}
