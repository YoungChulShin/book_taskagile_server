package task.agile.taskagile.domain.model.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import task.agile.taskagile.domain.common.security.PasswordEncryptor;
import task.agile.taskagile.domain.model.user.exceptions.InvalidPasswordException;
import task.agile.taskagile.domain.model.user.exceptions.LoginException;
import task.agile.taskagile.domain.model.user.exceptions.UsernameNotExistsException;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginManagement {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  public void login(String username, String password) throws LoginException {

    Optional<User> findUser;
    if (username.contains("@")) {
      findUser = repository.findByEmailAddress(username);
    } else {
      findUser = repository.findByUsername(username);
    }

    if (!findUser.isPresent()) {
      throw new UsernameNotExistsException();
    }

    User existingUser = findUser.get();
    if (!passwordEncoder.matches(password, existingUser.getPassword())) {
      throw new InvalidPasswordException();
    }
  }
}
