package com.taskagile.domain.application.impl;

import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.RegistrationManagement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

  private RegistrationManagement registrationManagementMock;
  private UserServiceImpl instance;

  @Before
  public void setup() {
    registrationManagementMock = mock(RegistrationManagement.class);
    instance = new UserServiceImpl(registrationManagementMock);
  }

  @Test (expected = IllegalArgumentException.class)
  public void register_nullCommand_shouldFail() throws RegistrationException {
    instance.register(null);
  }
}
