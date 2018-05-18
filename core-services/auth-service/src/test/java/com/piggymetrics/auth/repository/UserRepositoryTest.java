package com.piggymetrics.auth.repository;

import static org.junit.Assert.assertEquals;

import com.piggymetrics.auth.AuthApplication;
import com.piggymetrics.auth.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class UserRepositoryTest {

  @Autowired private UserRepository repository;

  @Test
  public void shouldSaveAndFindUserByName() {

    User user = new User();
    user.setUsername("name");
    user.setPassword("password");
    repository.save(user);

    User found = repository.findByUsername(user.getUsername()).get();
    assertEquals(user.getUsername(), found.getUsername());
    assertEquals(user.getPassword(), found.getPassword());
  }
}
