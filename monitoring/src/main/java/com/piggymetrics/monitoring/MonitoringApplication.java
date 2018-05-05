package com.piggymetrics.monitoring;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@Log4j2
@SpringBootApplication
@EnableHystrixDashboard
public class MonitoringApplication {
  // for spring-boot-2.0,x using host:port/actuator/hystrix.stream
  public static void main(String[] args) {
    final SpringApplication application = new SpringApplication(MonitoringApplication.class);
    application.setBannerMode(Banner.Mode.OFF);
    application.setWebApplicationType(WebApplicationType.SERVLET);
    application.run(args);
  }
}
