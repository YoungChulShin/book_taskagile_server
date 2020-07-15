package com.taskagile.web.results;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApiResultTest {

  @Test(expected = IllegalArgumentException.class)
  public void message_emptyMessage_shouldFail() {
    ApiResult.message("");

    fail();
  }
}
