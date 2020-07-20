package com.taskagile.domain.application.impl;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.common.mail.MailManager;
import com.taskagile.domain.common.mail.MessageVariable;
import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.RegistrationManagement;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.events.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

  private final RegistrationManagement registrationManagement;
  private final MailManager mailManager;
  private final DomainEventPublisher eventPublisher;

  @Override
  public void register(RegistrationCommand command) throws RegistrationException {

    Assert.notNull(command, "Parameter `command` must not be null");

    // 사용자 등록
    User newUser = registrationManagement.register(command.getUsername(), command.getEmailAddress(), command.getPassword());

    // 메일 전달
    mailManager.send(newUser.getEmailAddress(),
      "Welcome to TaskaAgile",
      "welcome.ftl",
      MessageVariable.from("user", newUser));

    // 이벤트 발생
    eventPublisher.publish(new UserRegisteredEvent(newUser));
  }
}
