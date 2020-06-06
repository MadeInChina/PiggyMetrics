package com.piggymetrics.auth.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.piggymetrics.auth.domain.User;
import com.piggymetrics.auth.repository.UserRepository;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UserServiceTest {

  @InjectMocks private UserServiceImpl userService;

  @Mock private UserRepository repository;

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void shouldCreateUser() {

    User user = new User();
    user.setUsername("name");
    user.setPassword("password");

    userService.create(user);
    verify(repository, times(1)).save(user);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFailWhenUserAlreadyExists() {

    User user = new User();
    user.setUsername("name");
    user.setPassword("password");

    when(repository.findByUsername(user.getUsername())).thenReturn(Optional.of(new User()));
    userService.create(user);
  }
}
