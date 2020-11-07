package com.taskagile.domain.model.team;

import com.taskagile.domain.model.team.events.TeamCreatedEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TeamCreatedEventHandler {

  private static final Logger log = LoggerFactory.getLogger(TeamCreatedEventHandler.class);

  @EventListener(TeamCreatedEvent.class)
  public void handleEvent(TeamCreatedEvent event) {
    log.debug("team registered! " + event.getTeam().getName());
  }
}
