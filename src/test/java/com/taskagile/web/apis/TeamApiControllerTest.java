package com.taskagile.web.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskagile.config.SecurityConfiguration;
import com.taskagile.web.payloads.CreateTeamPayload;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(TeamApiController.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {SecurityConfiguration.class, TeamApiController.class})
public class TeamApiControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper ObjectMapper;


  // 공백 이름이 들어오면 400 에러 리턴
  @Test
  public void createTeam_emtpyName_shouldReturn400() throws Exception {

    // given
    CreateTeamPayload payload = new CreateTeamPayload();
    payload.setName("");

    // when, then
    mvc.perform(MockMvcRequestBuilders.post("/api/teams")
        .contentType(MediaType.APPLICATION_JSON)
        .content(ObjectMapper.writeValueAsString(payload)))
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  public void createTeam_nullPayload_shouldReturn400() throws Exception {

    // when, then
    mvc.perform(MockMvcRequestBuilders.post("/api/teams"))
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }
}
