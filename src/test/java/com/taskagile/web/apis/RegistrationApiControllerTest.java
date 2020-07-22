package com.taskagile.web.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskagile.config.SecurityConfiguration;
import com.taskagile.domain.application.UserService;
import com.taskagile.domain.model.user.UsernameExistsException;
import com.taskagile.web.payloads.RegistrationPayload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationApiController.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {SecurityConfiguration.class, RegistrationApiController.class})
public class RegistrationApiControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserService userService;

  @Autowired
  private ObjectMapper objectMapper;

  // 빈 값이 들어왔을 때 BadRequest(400)을 리턴해야 한다
  @Test
  public void register_blankPayload_shouldReturn400() throws Exception {

    mvc.perform(post("/api/registrations"))
      .andExpect(status().is(400));
  }

  // 이미 존재하는 사용자를 등록하면 BadRequest(400)을 리턴해야 한다
  @Test
  public void register_existedUsername_shouldReturn400() throws Exception {

    RegistrationPayload payload = new RegistrationPayload();
    payload.setUsername("exists");
    payload.setEmailAddress("test@taskagile.com");
    payload.setPassword("132313");

    doThrow(UsernameExistsException.class)
      .when(userService)
      .register(payload.toCommand());

    mvc.perform(
      post("/api/registrations")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(payload)))
      .andExpect(status().is(400))
      .andExpect(jsonPath("$.message").value("Username already exists"));
  }
}
