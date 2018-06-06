package com.piggymetrics.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/** Define Spring is going to manage the creation, signing and translation of a JWT token. */
@Configuration
public class OAuth2ClientJwtConfig {

  @Autowired OAuth2ClientProperties oAuth2ClientProperties;

  @Autowired ResourceServerProperties resourceServerProperties;

  /** Use remote token services */
  @Primary
  @Bean
  public RemoteTokenServices tokenServices() {
    final RemoteTokenServices tokenService = new RemoteTokenServices();
    tokenService.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
    tokenService.setClientId(oAuth2ClientProperties.getClientId());
    tokenService.setClientSecret(oAuth2ClientProperties.getClientSecret());
    return tokenService;
  }
}
