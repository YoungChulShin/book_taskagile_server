package com.taskagile.domain.common.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultMailManger implements MailManager {

  private static final Logger log = LoggerFactory.getLogger(DefaultMailManger.class);

  @Override
  public void send(String emailAddress, String subject, String template, MessageVariable... variables) {
    // TODO: 메일 전달 - 구현 필요
    log.debug("Email delivered. " + emailAddress);
  }
}
