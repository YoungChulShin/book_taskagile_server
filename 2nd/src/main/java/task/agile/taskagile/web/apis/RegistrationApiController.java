package task.agile.taskagile.web.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import task.agile.taskagile.domain.UserService;
import task.agile.taskagile.web.payloads.RegistrationPayload;
import task.agile.taskagile.web.results.ApiResult;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationApiController {

  private final UserService userService;

  @PostMapping("/api/registration")
  public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload) {
    try {
      userService.register(payload.toCommand());
      return Result.created();
    }

  }
}