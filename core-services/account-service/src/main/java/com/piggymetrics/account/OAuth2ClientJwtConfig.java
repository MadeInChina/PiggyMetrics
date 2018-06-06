//package com.piggymetrics.account;
//
//import feign.RequestInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//
///** Define Spring is going to manage the creation, signing and translation of a JWT token. */
//@EnableResourceServer
//@Configuration
//public class OAuth2ClientJwtConfig extends ResourceServerConfigurerAdapter {
//
//  @Autowired OAuth2ClientProperties oAuth2ClientProperties;
//
//  @Autowired ResourceServerProperties resourceServerProperties;
//
//  /** Use remote token services */
//  @Primary
//  @Bean
//  public RemoteTokenServices tokenServices() {
//    final RemoteTokenServices tokenService = new RemoteTokenServices();
//    tokenService.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
//    tokenService.setClientId(oAuth2ClientProperties.getClientId());
//    tokenService.setClientSecret(oAuth2ClientProperties.getClientSecret());
//    return tokenService;
//  }
//
//  @Override
//  public void configure(ResourceServerSecurityConfigurer resources) {
//    resources.tokenServices(tokenServices());
//  }
//
//  @Override
//  public void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests()
//        .antMatchers("/", "/demo", "/actuator/**")
//        .permitAll()
//        .anyRequest()
//        .authenticated();
//  }
//
//  @Bean
//  @ConfigurationProperties(prefix = "security.oauth2.client")
//  public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
//    return new ClientCredentialsResourceDetails();
//  }
//
//  @Bean
//  public RequestInterceptor oauth2FeignRequestInterceptor() {
//    return new OAuth2FeignRequestInterceptor(
//        new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
//  }
//
//  @Bean
//  public OAuth2RestTemplate clientCredentialsRestTemplate() {
//    return new OAuth2RestTemplate(clientCredentialsResourceDetails());
//  }
//}
