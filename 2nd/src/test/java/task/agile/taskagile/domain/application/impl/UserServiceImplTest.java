package task.agile.taskagile.domain.application.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import task.agile.taskagile.domain.application.commands.RegistrationCommand;
import task.agile.taskagile.domain.common.event.DomainEventPublisher;
import task.agile.taskagile.domain.common.mail.MailManager;
import task.agile.taskagile.domain.model.user.RegistrationManagement;
import task.agile.taskagile.domain.model.user.UserRepository;
import task.agile.taskagile.domain.model.user.exceptions.RegistrationException;
import task.agile.taskagile.domain.model.user.exceptions.UsernameExistsException;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

  private RegistrationManagement registrationManagementMock;
  private DomainEventPublisher eventPublisherMock;
  private MailManager mailManagerMock;
  private UserServiceImpl instance;
  private UserRepository userRepositoryMock;

  @BeforeEach
  public void setup() {
    registrationManagementMock = Mockito.mock(RegistrationManagement.class);
    eventPublisherMock = Mockito.mock(DomainEventPublisher.class);
    mailManagerMock = Mockito.mock(MailManager.class);
    userRepositoryMock = Mockito.mock(UserRepository.class);

    instance = new UserServiceImpl(registrationManagementMock, eventPublisherMock, mailManagerMock, userRepositoryMock);
  }

  @Test
  void register_nullCommand_shouldFail() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> instance.register(null));
  }

  @Test
  void register_existingUsername_shouldFail() throws RegistrationException {
    String username = "existing";
    String emailAddress = "go1323@gmail.com";
    String password = "testPassword";

    Mockito.doThrow(UsernameExistsException.class)
      .when(registrationManagementMock)
      .register(username, emailAddress, password);

    RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);

    Assertions.assertThrows(UsernameExistsException.class, () -> instance.register(command));
  }

  // TODO - register_validCommand_shouldSucceed 구현
  /*@Test
  void register_validCommand_shouldSucceed() {
  }*/
}
