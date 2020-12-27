package task.agile.taskagile.domain.application;

import org.springframework.security.core.userdetails.UserDetailsService;
import task.agile.taskagile.domain.application.commands.RegistrationCommand;
import task.agile.taskagile.domain.model.user.exceptions.RegistrationException;

public interface UserService extends UserDetailsService {

  void register(RegistrationCommand command) throws RegistrationException;
}
