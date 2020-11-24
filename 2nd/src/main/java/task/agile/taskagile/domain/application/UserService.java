package task.agile.taskagile.domain.application;

import task.agile.taskagile.domain.application.commands.RegistrationCommand;
import task.agile.taskagile.domain.model.user.exceptions.RegistrationException;

public interface UserService {

  void register(RegistrationCommand command) throws RegistrationException;
}
