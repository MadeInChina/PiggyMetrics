package com.piggymetrics.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/** Define Spring is going to manage the creation, signing and translation of a JWT token. */
@Configuration
public class OAuth2ClientJwtConfig {

  /** Use default token services */
  @Bean
  @Primary
  public DefaultTokenServices tokenServices(TokenStore tokenStore) {
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(tokenStore);
    defaultTokenServices.setSupportRefreshToken(true);
    return defaultTokenServices;
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
    converter.setSigningKey("for-test-only");
    return converter;
  }
}
