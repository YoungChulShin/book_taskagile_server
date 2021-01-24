package task.agile.taskagile.web.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import task.agile.taskagile.config.security.JwtTokenProvider;
import task.agile.taskagile.domain.application.UserService;
import task.agile.taskagile.domain.model.user.exceptions.InvalidPasswordException;
import task.agile.taskagile.domain.model.user.exceptions.LoginException;
import task.agile.taskagile.domain.model.user.exceptions.UsernameNotExistsException;
import task.agile.taskagile.web.payloads.LoginPayload;
import task.agile.taskagile.web.results.ApiResult;
import task.agile.taskagile.web.results.Result;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class LoginApiController {

  private final UserService userService;
  private final JwtTokenProvider tokenProvider;

  @PostMapping("/api/login")
  public ResponseEntity<ApiResult> login(@RequestBody LoginPayload loginPayload) {
    try {
      userService.login(loginPayload.toCommand());

      String token = tokenProvider.createToken(loginPayload.getUsername(), Arrays.asList("ROLE_USER"));
      return Result.ok(ApiResult.blank().add("token", token));
    } catch (LoginException e) {
      String errorMessage = "Login failed";
      if (e instanceof UsernameNotExistsException) {
        errorMessage = "Username not exists";
      } else if (e instanceof InvalidPasswordException) {
        errorMessage = "Invalid password";
      }
      return Result.failure(errorMessage);
    }
  }

  @PostMapping("/api/test")
  public ResponseEntity<ApiResult> test() {
    return Result.ok("test");
  }
}
