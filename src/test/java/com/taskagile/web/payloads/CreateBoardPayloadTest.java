package com.taskagile.web.payloads;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class CreateBoardPayloadTest {

  private Validator validator;

  @Before
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void validate_emptyName_shouldFail() {

    // given
    CreateBoardPayload payload = new CreateBoardPayload();
    payload.setName("");

    // when
    Set<ConstraintViolation<CreateBoardPayload>> validates = validator.validate(payload);

    // then
    assertEquals(1, validates.size());
  }
}
