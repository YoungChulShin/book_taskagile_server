package com.taskagile.domain.common.mail;

import lombok.Getter;

import java.util.Objects;

@Getter
public class MessageVariable {

  private String key;
  private Object value;

  private MessageVariable(String key, Object value) {
    this.key = key;
    this.value = value;
  }

  public static MessageVariable from(String key, Object value) {
    return new MessageVariable(key, value);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof MessageVariable)) {
      return false;
    }

    MessageVariable that = (MessageVariable)obj;
    return Objects.equals(key, that.getKey()) &&
      Objects.equals(value, that.getValue());
  }
}
