package task.agile.taskagile.domain.common.security;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptorDelegator implements PasswordEncryptor{
  @Override
  public String encrypt(String rawPassword) {
    // TODO - 구현 필요
    return rawPassword;
  }
}
