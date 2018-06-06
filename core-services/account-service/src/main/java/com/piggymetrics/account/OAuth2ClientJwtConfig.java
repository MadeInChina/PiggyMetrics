package com.piggymetrics.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

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

  @Bean
  public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
    return new JwtTokenStore(jwtAccessTokenConverter);
  }

  /**
   * How the token will be translated based on a signing key Symmetrical key => both the service and
   * auth service need to have the same signing key
   */
  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey("123456");
    return converter;
  }
}
