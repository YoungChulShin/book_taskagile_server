package com.taskagile.domain.common.event;

import org.springframework.context.ApplicationEvent;

public abstract class DomainEvent extends ApplicationEvent {
  /**
   *
   */
  private static final long serialVersionUID = 567458703720151467L;

  /**
   * Create a new {@code ApplicationEvent}.
   *
   * @param source the object on which the event initially occurred or with which
   *               the event is associated (never {@code null})
   */
  public DomainEvent(Object source) {
    super(source);
  }

  public long occuredAt() {
    return getTimestamp();
  }
}
