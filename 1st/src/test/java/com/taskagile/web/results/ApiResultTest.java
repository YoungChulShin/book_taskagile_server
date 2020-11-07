package com.taskagile.web.results;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApiResultTest {

  @Test(expected = IllegalArgumentException.class)
  public void message_emptyMessage_shouldFail() {
    ApiResult.message("");

    fail();
  }

  @Test
  public void add_validData_shouldSucceed() {

    // given
    String key = "testKey";
    String value = "testValue";

    // when
    ApiResult apiResult = ApiResult.blank().add(key, value);

    // then
    assertNotNull("Added data should not be null", apiResult.get(key));
    assertEquals(1, apiResult.keySet().size());
    assertEquals(value, apiResult.get(key));
  }
}
