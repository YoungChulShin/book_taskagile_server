package task.agile.taskagile.web.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import task.agile.taskagile.domain.application.UserService;
import task.agile.taskagile.domain.model.user.exceptions.EmailAddressExistsException;
import task.agile.taskagile.domain.model.user.exceptions.RegistrationException;
import task.agile.taskagile.domain.model.user.exceptions.UsernameExistsException;
import task.agile.taskagile.web.payloads.RegistrationPayload;
import task.agile.taskagile.web.results.ApiResult;
import task.agile.taskagile.web.results.Result;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationApiController {

  private final UserService service;

  @PostMapping("/api/registrations")
  public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload) {
    try {
      service.register(payload.toCommand());
      return Result.created();
    } catch (RegistrationException e) {
      String errorMessage = "Registration failed";
      if (e instanceof UsernameExistsException) {
        errorMessage = "Username already exists";
      } else if (e instanceof EmailAddressExistsException) {
        errorMessage = "Emailaddress already exists";
      }
      return Result.failure(errorMessage);
    }
  }
}
