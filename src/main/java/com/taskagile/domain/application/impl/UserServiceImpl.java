package com.taskagile.domain.application.Impl;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public void register(RegistrationCommand command) throws RegistrationException {

    Assert.notNull(command, "Paraemter `command` must not be null");

  }
}
