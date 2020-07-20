package com.taskagile.domain.model.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test(expected = DataIntegrityViolationException.class)
  public void save_nullUsername_shouldFail() {
    User user = User.create(null, "test@test.com", "123456789");

    userRepository.save(user);

    fail();
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void save_nullEmailAddress_shouldFail() {
    User user = User.create("testuser", null, "123456789");

    userRepository.save(user);

    fail();
  }

  @Test
  public void save_validUser_shouldSuccess() {
    User user = User.create("testuser", "test@test.com", "123456789");
    userRepository.save(user);

    assertNotNull(user.getId());
    assertNotNull(user.getCreatedDate());
    assertEquals("testuser", user.getUsername());
    assertEquals("test@test.com", user.getEmailAddress());
  }

  @Test
  public void findByUsername_exist_shouldReturnResult() {
    User user = User.create("testuser", "test@test.com", "123456789");
    userRepository.save(user);

    User findUser = userRepository.findByUsername(user.getUsername());
    assertEquals(user, findUser);
  }

  @Test
  public void findByUsername_notExist_shouldReturnEmptyResult() {
    User findUser = userRepository.findByUsername("testuser");
    assertNull(findUser);
  }
}
