package com.taskagile.web.payloads;

import org.junit.Before;
import org.junit.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;

public class RegistrationPayloadTest {

  private Validator validator;

  @Before
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void validate_blankPayload_shouldFail() {
    RegistrationPayload payload = new RegistrationPayload();
    Set<ConstraintViolation<RegistrationPayload>> validations = validator.validate(payload);
    assertEquals(3, validations.size());
  }

  @Test
  public void validate_payloadWithUsernameShorterThan2_shouldFail() {
    RegistrationPayload payload = new RegistrationPayload();
    payload.setUsername("S");
    payload.setPassword("testPassword");
    payload.setEmailAddress("sunny@taskagile.com");

    Set<ConstraintViolation<RegistrationPayload>> validations = validator.validate(payload);
    assertEquals(1, validations.size());
  }
}
