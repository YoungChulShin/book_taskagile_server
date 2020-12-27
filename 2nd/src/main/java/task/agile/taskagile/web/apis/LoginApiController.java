package task.agile.taskagile.web.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import task.agile.taskagile.domain.application.UserService;
import task.agile.taskagile.web.results.ApiResult;
import task.agile.taskagile.web.results.Result;

@Controller
@RequiredArgsConstructor
public class LoginApiController {

  private final UserService userService;

  // 로그인을 하고 토큰을 리턴


  @PostMapping("/test")
  public ResponseEntity<ApiResult> test() {
    return Result.ok("OK");
  }
}
