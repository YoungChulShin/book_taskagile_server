package task.agile.taskagile.domain.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import task.agile.taskagile.domain.common.security.PasswordEncryptor;
import task.agile.taskagile.domain.model.user.exceptions.EmailAddressExistsException;
import task.agile.taskagile.domain.model.user.exceptions.RegistrationException;
import task.agile.taskagile.domain.model.user.exceptions.UsernameExistsException;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RegistrationManagement {

  private final UserRepository repository;
  private final PasswordEncryptor passwordEncryptor;

  public User register(String username, String emailAddress, String password) throws RegistrationException {
    Optional<User> existingUser = repository.findByUsername(username);
    if (existingUser.isPresent()) {
      throw new UsernameExistsException();
    }

    existingUser = repository.findByEmailAddress(emailAddress);
    if (existingUser.isPresent()) {
      throw new EmailAddressExistsException();
    }

    String encryptedPassword = passwordEncryptor.encrypt(password);
    User newUser = User.create(username, emailAddress, encryptedPassword);
    repository.save(newUser);

    return newUser;
  }
}
