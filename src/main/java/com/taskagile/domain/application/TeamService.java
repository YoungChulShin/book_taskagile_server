package com.taskagile.domain.application;

import com.taskagile.domain.application.commands.CreateTeamCommand;
import com.taskagile.domain.model.team.Team;

public interface TeamService {

  Team createTeam(CreateTeamCommand command);
}
