package com.taskagile.web.apis;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.application.model.user.RegistrationException;
import com.taskagile.web.payloads.RegistrationPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationApiController {

  private final UserService userService;


  @PostMapping("/api/registrations")
  public ResponseEntity<String> register(@Valid @RequestBody RegistrationPayload payload) {

    try {
      // 등록
      userService.register(payload.toCommand());

    } catch (RegistrationException e) {

    }
    // 결과 리턴

    return null;
  }
}
