package com.piggymetrics.account.client;

import com.piggymetrics.account.domain.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service", fallbackFactory = AuthServiceClientFallbackFactory.class)
public interface AuthServiceClient {

  @RequestMapping(
    method = RequestMethod.POST,
    value = "/uaa/users",
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  void createUser(User user);
}

@Log4j2
@Component
class AuthServiceClientFallbackFactory implements FallbackFactory<AuthServiceClient> {

  @Override
  public AuthServiceClient create(Throwable cause) {
    return user -> {
      log.info("fallback; reason was:", cause.getMessage());

      log.info(
          "******** AUTH SERVICE FAILED! - FALLING BACK create username {}", user.getUsername());
    };
  }
}
