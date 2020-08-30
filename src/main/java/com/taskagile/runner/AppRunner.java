package com.taskagile.runner;

import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.application.impl.UserServiceImpl;
import com.taskagile.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

  @Autowired
  UserServiceImpl userService;

  @Autowired
  UserRepository userRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    // 사용자 등록
    RegistrationCommand registrationCommand = new RegistrationCommand(
      "youngchulshin", "go1323@gmail.com", "tlsdudcjf30@!");
    userService.register(registrationCommand);

  }
}
