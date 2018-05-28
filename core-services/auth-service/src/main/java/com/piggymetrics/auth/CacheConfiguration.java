package com.piggymetrics.auth;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;

@ImportResource({"classpath:oauth2-cache.xml"})
@Configuration
@EnableCaching
public class CacheConfiguration {

  @Bean
  public CacheManager ehCacheCacheManager() {
    EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
    bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
    EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
    ehCacheCacheManager.setCacheManager(bean.getObject());
    return ehCacheCacheManager;
  }
}
