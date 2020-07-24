package com.taskagile.domain.model.user;

import com.taskagile.domain.common.security.PasswordEncryptor;
import com.taskagile.domain.model.user.exceptions.EmailAddressExistsException;
import com.taskagile.domain.model.user.exceptions.RegistrationException;
import com.taskagile.domain.model.user.exceptions.UsernameExistsException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RegistrationManagement {

  private final UserRepository userRepository;
  private final PasswordEncryptor passwordEncryptor;

  public User register(String username, String emailAddress, String password) throws RegistrationException {

    User existsUser = userRepository.findByUsername(username);
    if (existsUser != null) {
      throw new UsernameExistsException();
    }

    existsUser = userRepository.findByEmailAddress(emailAddress);
    if (existsUser != null) {
      throw new EmailAddressExistsException();
    }

    String encryptedPassword = passwordEncryptor.encrypt(password);
    User user = User.create(username, emailAddress.toLowerCase(), encryptedPassword);

    userRepository.save(user);

    return user;
  }
}
