package com.taskagile.web.apis.authenticate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taskagile.utils.JsonUtils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

  public AuthenticationFilter() {
    super(new AntPathRequestMatcher("/api/authentications", "POST"));
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {

        // xxx-from-urlencoded를 Object로 변경
        log.debug("Processing login request");
        String requestBody = IOUtils.toString(request.getReader());
        LoginRequest loginRequest = JsonUtils.toObject(requestBody, LoginRequest.class);

        if (loginRequest == null || loginRequest.isInvalid()) {
          throw new InsufficientAuthenticationException("Invalid authentication request");
        }

        // 인증에 요청에 문제가 없으면 토큰 생성
        UsernamePasswordAuthenticationToken token =
          new UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password);

        // AbstractAuthenticationProcessingFilter가 가지고 있는 AuthenticationManager에게 인증 요청
        return this.getAuthenticationManager().authenticate(token);
  }

  @Getter
  @Setter
  static class LoginRequest {
    private String username;
    private String password;

    public boolean isInvalid() {
      return StringUtils.isEmpty(username) || StringUtils.isEmpty(password);
    }
  }

}
