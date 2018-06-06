package com.piggymetrics.auth;

import com.piggymetrics.auth.service.security.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private MongoUserDetailsService userDetailsService;

  @Autowired private Environment env;

  @Autowired private JwtTokenStore jwtTokenStore;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // TODO persist clients details
    clients
        .inMemory()
        .withClient("browser")
        .authorizedGrantTypes("refresh_token", "password")
        .scopes("ui")
        .and()
        .withClient("account-service")
        .secret(env.getProperty("ACCOUNT_SERVICE_PASSWORD"))
        .authorizedGrantTypes("client_credentials", "refresh_token")
        .authorities("ROLE_TRUSTED_CLIENT")
        .scopes("server")
        .and()
        .withClient("statistics-service")
        .secret(env.getProperty("STATISTICS_SERVICE_PASSWORD"))
        .authorizedGrantTypes("client_credentials", "refresh_token")
        .authorities("ROLE_TRUSTED_CLIENT")
        .scopes("server")
        .and()
        .withClient("notification-service")
        .secret(env.getProperty("NOTIFICATION_SERVICE_PASSWORD"))
        .authorizedGrantTypes("client_credentials", "refresh_token")
        .authorities("ROLE_TRUSTED_CLIENT")
        .scopes("server");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints
        .tokenStore(jwtTokenStore)
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
    oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
  }

  @Bean
  public JwtTokenStore tokenStore() {
    JwtAccessTokenConverter enhancer = new JwtAccessTokenConverter();
    enhancer.setSigningKey("123456");
    return new JwtTokenStore(enhancer);
  }

  @Bean
  @Primary
  public DefaultTokenServices tokenServices() {
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(tokenStore());
    defaultTokenServices.setSupportRefreshToken(true);
    return defaultTokenServices;
  }
}
