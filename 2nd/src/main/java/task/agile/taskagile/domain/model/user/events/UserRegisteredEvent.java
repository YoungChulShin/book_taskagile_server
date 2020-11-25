package task.agile.taskagile.domain.model.user.events;

import lombok.Getter;
import org.springframework.util.Assert;
import task.agile.taskagile.domain.common.event.DomainEvent;
import task.agile.taskagile.domain.model.user.User;

import java.util.Objects;

@Getter
public class UserRegisteredEvent extends DomainEvent {

  private static final long serialVersionUID = -8844692029862548796L;

  private User user;

  public UserRegisteredEvent(User user) {
    super(user);
    Assert.notNull(user, "Parameter 'user' must not be null");
    this.user = user;
  }

  @Override
  public String toString() {
    return "UserRegisteredEvent{" +
      "user=" + user + '\'' +
      "timestamp'=" + getTimestamp() + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserRegisteredEvent that = (UserRegisteredEvent) o;
    return Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user);
  }
}
