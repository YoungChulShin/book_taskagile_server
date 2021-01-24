package task.agile.taskagile.domain.common.mail;

import freemarker.template.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import static org.junit.jupiter.api.Assertions.*;

class DefaultMailManagerTest {

  @TestConfiguration
  static class DefaultMessageCreatorConfiguration {
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreemarkerConfiguration() {
      FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
      factoryBean.setTemplateLoaderPath("/mail-templates/");
      return factoryBean;
    }
  }

  @Autowired
  private Configuration configuration;
  private Mailer mailerMock;
  private DefaultMailManager instance;

  @BeforeEach
  public void setup() {
    mailerMock = Mockito.mock(Mailer.class);
    instance = new DefaultMailManager("noreply@taskagile.com", mailerMock, configuration);
  }

  @Test
  void send_validParameters_shouldSucceed() {
    String to = "user@example.com";
    String subject = "Test subject";
    String templateName = "test.ftl";

    instance.send(to, subject, templateName, MessageVariable.from("name", "test"));
    ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
    Mockito.verify(mailerMock).send(messageArgumentCaptor.capture());

    Message messageSent = messageArgumentCaptor.getValue();
    assertEquals(subject, messageSent.getSubject());
    assertEquals("noreply@taskagile.com", messageSent.getFrom());
  }
}
