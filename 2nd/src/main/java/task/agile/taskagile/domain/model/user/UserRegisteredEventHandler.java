package task.agile.taskagile.domain.model.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import task.agile.taskagile.domain.model.user.events.UserRegisteredEvent;

@Component
@Slf4j
public class UserRegisteredEventHandler {

  @EventListener(UserRegisteredEvent.class)
  public void handleEvent(UserRegisteredEvent event) {
    log.debug("User registered! " + event.getUser().toString());
  }
}
