package task.agile.taskagile.domain.common.mail;

import freemarker.template.Configuration;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultMailManager implements MailManager {

  private String mailFrom;
  private Mailer mailer;
  private Configuration configuration;

  public DefaultMailManager(@Value("${app.mail-from}") String mailFrom,
                            Mailer mailer,
                            Configuration configuration) {
    this.mailFrom = mailFrom;
    this.mailer = mailer;
    this.configuration = configuration;
  }

  @Override
  public void send(String emailAddress, String subject, String template, MessageVariable... variables) {
    Assert.hasText(emailAddress, "Parameter 'email address' must not be blank");
    Assert.hasText(subject, "Parameter 'subject' must not be blank");
    Assert.hasText(template, "Parameter 'template' must not be blank");

    String messageBody = createMessageBody(template, variables);
    Message message = new SimpleMessage(emailAddress, subject, messageBody, mailFrom);
    mailer.send(message);
  }

  private String createMessageBody(String template, MessageVariable... variables) {
    // TODO
    return "";
  }
}
