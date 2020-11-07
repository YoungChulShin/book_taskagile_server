package com.taskagile.domain.common.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PasswordEncryptorDelegator implements PasswordEncryptor {

  private final PasswordEncoder encoder;

  @Override
  public String encrypt(String rawPassword) {
    return encoder.encode(rawPassword);
  }
}
