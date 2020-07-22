package com.taskagile.domain.application;

import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.model.user.RegistrationException;

import org.springframework.security.core.userdetails.UserDetailsService;

// UserDetailsService는 SpringSecuritry에서 사용자 인증을 하는 과정에서 사용자 정보를 가져오는 기능을 처리한다
public interface UserService extends UserDetailsService {

  void register(RegistrationCommand command) throws RegistrationException;
}
