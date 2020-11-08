package com.taskagile.web.apis;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.model.user.exceptions.EmailAddressExistsException;
import com.taskagile.domain.model.user.exceptions.RegistrationException;
import com.taskagile.domain.model.user.exceptions.UsernameExistsException;
import com.taskagile.web.payloads.RegistrationPayload;
import com.taskagile.web.results.ApiResult;
import com.taskagile.web.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegistrationApiController {

  private final UserService userService;

  @PostMapping("/api/registrations")
  public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload) {

    try {
      userService.register(payload.toCommand());
      return Result.created();
    }
    catch (RegistrationException e) {
      String errorMessage = "Registration failed";
      if (e instanceof UsernameExistsException) {
        errorMessage = "Username already exists";
      } else if (e instanceof EmailAddressExistsException) {
        errorMessage = "Email address already exists";
      }
      return Result.failure(errorMessage);
    }RegistrationControllerTest
  }
}
