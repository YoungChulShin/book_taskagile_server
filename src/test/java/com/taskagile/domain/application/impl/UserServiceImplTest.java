package com.taskagile.domain.application.impl;

import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.common.mail.MailManager;
import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.RegistrationManagement;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UsernameExistsException;
import com.taskagile.domain.model.user.events.UserRegisteredEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

  private RegistrationManagement registrationManagementMock;
  private DomainEventPublisher eventPublisherMock;
  private MailManager mailManagerMock;
  private UserServiceImpl instance;

  @Before
  public void setup() {
    registrationManagementMock = mock(RegistrationManagement.class);
    eventPublisherMock = mock(DomainEventPublisher.class);
    mailManagerMock = mock(MailManager.class);
    instance = new UserServiceImpl(registrationManagementMock, mailManagerMock, eventPublisherMock);
  }

  @Test (expected = IllegalArgumentException.class)
  public void register_nullCommand_shouldFail() throws RegistrationException {
    instance.register(null);
  }

  @Test (expected = UsernameExistsException.class)
  public void register_existingUsername_shouldFail() throws RegistrationException {

    String username = "existing";
    String emailAddress = "go1323@gmail.com";
    String password = "testPassword";

    Mockito.doThrow(UsernameExistsException.class)
      .when(registrationManagementMock)
      .register(username, emailAddress, password);

    RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);
    instance.register(command);
  }

  @Test
  public void register_validCommand_shouldSucceed() throws RegistrationException {
    String username = "existing";
    String emailAddress = "go1323@gmail.com";
    String password = "testPassword";
    User newUser = User.create(username, emailAddress, password);

    Mockito.when(registrationManagementMock.register(username, emailAddress, password))
      .thenReturn(newUser);

    RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);
    instance.register(command);

    Mockito.verify(eventPublisherMock)
      .publish(new UserRegisteredEvent(newUser));
  }
}