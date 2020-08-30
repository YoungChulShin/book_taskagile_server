package com.taskagile.domain.application.impl;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.common.mail.MailManager;
import com.taskagile.domain.common.mail.MessageVariable;
import com.taskagile.domain.model.user.RegistrationManagement;
import com.taskagile.domain.model.user.SimpleUser;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserRepository;
import com.taskagile.domain.model.user.events.UserRegisteredEvent;
import com.taskagile.domain.model.user.exceptions.RegistrationException;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

  private final RegistrationManagement registrationManagement;
  private final MailManager mailManager;
  private final DomainEventPublisher eventPublisher;
  private final UserRepository userRepository;

  @Transactional
  @Override
  public void register(RegistrationCommand command) throws RegistrationException {

    Assert.notNull(command, "Parameter `command` must not be null");

    // 사용자 등록
    User newUser = registrationManagement.register(command.getUsername(), command.getEmailAddress(),
        command.getPassword());

    // 메일 전달
    mailManager.send(newUser.getEmailAddress(), "Welcome to TaskaAgile", "welcome.ftl",
        MessageVariable.from("user", newUser));

    // 이벤트 발생
    eventPublisher.publish(new UserRegisteredEvent(newUser));
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    if (StringUtils.isEmpty(username)) {
      throw new UsernameNotFoundException("No user found");
    }

    User user;
    if (username.contains("@")) {
      user = userRepository.findByEmailAddress(username);
    } else {
      user = userRepository.findByUsername(username);
    }

    if (user == null) {
      throw new UsernameNotFoundException("No user found by " + username);
    }

    return new SimpleUser(user);
  }

  @Override
  public User findUserById(Long userId) {
    return userRepository.findById(userId).orElse(null);
  }


}
