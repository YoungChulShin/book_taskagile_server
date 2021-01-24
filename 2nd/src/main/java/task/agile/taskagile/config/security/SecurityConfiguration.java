package task.agile.taskagile.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final JwtTokenProvider jwtTokenProvider;

  private static final String[] PUBLIC =
    new String[] {"/error", "/api/login", "/logout", "/register", "/api/registrations"};

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .httpBasic().disable()  // 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 되기 때문에 비활성화
      .csrf().disable()   // csrf 보안 비활성화
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)   // jwt token 인증이기 때문에 session 비활성화
      .and()
        .authorizeRequests()
          .antMatchers(PUBLIC).permitAll()
          .anyRequest().authenticated()
      .and()
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
      "/swagger-ui.html", "/webjars/**", "/swagger/**");
  }
}
