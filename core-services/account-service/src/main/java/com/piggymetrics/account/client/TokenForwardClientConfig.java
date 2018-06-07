package com.piggymetrics.account.client;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class TokenForwardClientConfig {
  @Bean
  @SuppressWarnings("static-method")
  public RequestInterceptor requestInterceptor() {
    return template -> {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth != null) {
        Object details = auth.getDetails();
        if (details instanceof OAuth2AuthenticationDetails) {
          OAuth2AuthenticationDetails holder = (OAuth2AuthenticationDetails) details;
          template.header(
              HttpHeaders.AUTHORIZATION, holder.getTokenType() + " " + holder.getTokenValue());
        }
      }
    };
  }
}
