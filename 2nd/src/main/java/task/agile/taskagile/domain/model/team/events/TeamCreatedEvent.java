package task.agile.taskagile.domain.model.team.events;

import lombok.Getter;
import task.agile.taskagile.domain.common.event.DomainEvent;
import task.agile.taskagile.domain.model.team.Team;

import java.util.Objects;

@Getter
public class TeamCreatedEvent extends DomainEvent {

  private static final long serialVersionUID = -1287721666383025150L;

  private Team team;

  public TeamCreatedEvent(Team team) {
    super(team);
    this.team = team;
  }

  @Override
  public String toString() {
    return "TeamCreatedEvent{" +
      "team=" + team +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TeamCreatedEvent that = (TeamCreatedEvent) o;
    return Objects.equals(team, that.team);
  }

  @Override
  public int hashCode() {
    return Objects.hash(team);
  }
}
