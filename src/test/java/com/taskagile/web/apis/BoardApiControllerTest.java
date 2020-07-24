package com.taskagile.web.apis;

import com.taskagile.config.SecurityConfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@WebMvcTest(BoardApiController.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SecurityConfiguration.class, BoardApiController.class})
public class BoardApiControllerTest {

  @Autowired
  private MockMvc mvc;

  // nullcommand에 대한 처리
  @Test
  public void createBoard_nullCommand_shouldFail() throws Exception {

    mvc.perform(MockMvcRequestBuilders.post("/api/boards"))
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  // 올바른 커맨드에 대한 처리
}
