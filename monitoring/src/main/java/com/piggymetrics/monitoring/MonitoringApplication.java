package com.piggymetrics.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.cloud.stream.converter.CompositeMessageConverterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.converter.ConfigurableCompositeMessageConverter;

@SpringBootApplication
@EnableTurbineStream
@EnableHystrixDashboard
public class MonitoringApplication {
  // for spring-boot-2.0,x using host:port/actuator/hystrix.stream
  public static void main(String[] args) {
    SpringApplication.run(MonitoringApplication.class, args);
  }

  @Bean
  public ConfigurableCompositeMessageConverter integrationArgumentResolverMessageConverter(
      CompositeMessageConverterFactory factory) {
    return new ConfigurableCompositeMessageConverter(
        factory.getMessageConverterForAllRegistered().getConverters());
  }
}
