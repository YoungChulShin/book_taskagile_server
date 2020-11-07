package com.taskagile.domain.application;

import com.taskagile.domain.application.commands.CreateTeamCommand;
import com.taskagile.domain.model.team.Team;
import com.taskagile.domain.model.user.exceptions.UserNotFoundException;

public interface TeamService {

  Team createTeam(CreateTeamCommand command) throws UserNotFoundException;
}
