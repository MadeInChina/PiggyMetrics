package com.piggymetrics.auth;

import com.piggymetrics.auth.service.security.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private MongoUserDetailsService userDetailsService;

  @Autowired private Environment env;

  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // TODO persist clients details
    clients
        .inMemory()
        .withClient("browser")
        .secret("{noop}")
        .authorizedGrantTypes("refresh_token", "password")
        .scopes("ui")
        .and()
        .withClient("account-service")
        .secret(passwordEncoder.encode(env.getProperty("ACCOUNT_SERVICE_PASSWORD", "{noop}password")))
        .authorizedGrantTypes("client_credentials", "refresh_token")
        .scopes("server")
        .and()
        .withClient("statistics-service")
        .secret(passwordEncoder.encode(env.getProperty("STATISTICS_SERVICE_PASSWORD", "{noop}password")))
        .authorizedGrantTypes("client_credentials", "refresh_token")
        .scopes("server")
        .and()
        .withClient("notification-service")
        .secret(passwordEncoder.encode(env.getProperty("NOTIFICATION_SERVICE_PASSWORD", "{noop}password")))
        .authorizedGrantTypes("client_credentials", "refresh_token")
        .scopes("server");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints
        .tokenStore(new InMemoryTokenStore())
        .accessTokenConverter(accessTokenConverter())
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
    oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter enhancer = new JwtAccessTokenConverter();
    enhancer.setSigningKey("123456");
    return enhancer;
  }
}
