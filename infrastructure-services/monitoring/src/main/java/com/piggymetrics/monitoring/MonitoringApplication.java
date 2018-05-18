package com.piggymetrics.monitoring;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@Log4j2
@SpringBootApplication
@EnableHystrixDashboard
public class MonitoringApplication {
  public static void main(String[] args) {
    SpringApplication.run(MonitoringApplication.class, args);
  }
}
