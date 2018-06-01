package com.piggymetrics.auth;

import java.io.IOException;
import javax.annotation.PreDestroy;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;

@ImportResource({"classpath:oauth2-cache.xml"})
@Configuration
@EnableCaching
public class CacheConfiguration {

  private net.sf.ehcache.CacheManager cacheManager;

  @PreDestroy
  public void destroy() {
    cacheManager.shutdown();
  }

  @Bean
  public CacheManager ehCacheCacheManager() throws IOException {
    cacheManager = net.sf.ehcache.CacheManager.create(new ClassPathResource("ehcache.xml").getURL());
    EhCacheCacheManager ehCacheManager = new EhCacheCacheManager();
    ehCacheManager.setCacheManager(cacheManager);
    return ehCacheManager;
  }
}
