package com.taskagile.domain.application.impl;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.RegistrationManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

  private final RegistrationManagement registrationManagement;

  @Override
  public void register(RegistrationCommand command) throws RegistrationException {

    Assert.notNull(command, "Parameter `command` must not be null");

    registrationManagement.register(command.getUsername(), command.getEmailAddress(), command.getPassword());
  }
}
