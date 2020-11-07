package com.taskagile.domain.model.team.events;

import com.taskagile.domain.common.event.DomainEvent;
import com.taskagile.domain.model.team.Team;

import lombok.Getter;

@Getter
public class TeamCreatedEvent extends DomainEvent {

  private static final long serialVersionUID = 2714833255396717504L;

  private Team team;

  public TeamCreatedEvent(Object source, Team team) {
    super(source);
    this.team = team;
  }

  @Override
  public String toString() {
    return "TeamCreatedEvent{" +
      "team='" + team + '\'' +
      "timestamp='" + getTimestamp() + '\'' +
      '}';
  }

  @Override
  public int hashCode() {
    return team.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof TeamCreatedEvent)) {
      return false;
    }

    TeamCreatedEvent that = (TeamCreatedEvent) o;
    return that.getTeam().equals(team);
  }
}
