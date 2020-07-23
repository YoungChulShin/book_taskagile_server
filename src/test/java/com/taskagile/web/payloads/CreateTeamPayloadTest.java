package com.taskagile.web.payloads;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;


public class CreateTeamPayloadTest {

  private Validator validator;

  @Before
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }


  @Test
  public void validate_blankPayload_shouldFail() {

    // given
    CreateTeamPayload payload = new CreateTeamPayload();

    // when
    Set<ConstraintViolation<CreateTeamPayload>> validations = validator.validate(payload);

    // then
    assertEquals(1, validations.size());
  }

  @Test
  public void validate_emptyPayload_shouldFail() {

    // given
    CreateTeamPayload payload = new CreateTeamPayload();
    payload.setName("");

    // when
    Set<ConstraintViolation<CreateTeamPayload>> validations = validator.validate(payload);

    // then
    assertEquals(1, validations.size());
  }
}
