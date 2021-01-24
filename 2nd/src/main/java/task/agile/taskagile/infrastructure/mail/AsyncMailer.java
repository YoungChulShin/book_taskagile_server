package task.agile.taskagile.infrastructure.mail;

import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import task.agile.taskagile.domain.common.mail.Mailer;
import task.agile.taskagile.domain.common.mail.Message;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Component
public class AsyncMailer implements Mailer {

  private JavaMailSender mailSender;

  public AsyncMailer(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void send(Message message) {
    Assert.notNull(message, "Parameter 'message' must not be null");

    try {
      SimpleMailMessage mailMessage = new SimpleMailMessage();

      if (StringUtils.isNotBlank(message.getFrom())) {
        mailMessage.setFrom(message.getFrom());
      }
      if (StringUtils.isNotBlank(message.getSubject())) {
        mailMessage.setSubject(message.getSubject());
      }
      if (StringUtils.isNotEmpty(message.getBody())) {
        mailMessage.setText(message.getBody());
      }
      if (message.getTo() != null) {
        mailMessage.setTo(message.getTo());
      }

      mailSender.send(mailMessage);
    } catch (MailException e) {
      log.error("Failed to send mail message", e);
    }
  }
}
