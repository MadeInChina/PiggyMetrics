package com.piggymetrics.monitoring;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTurbineStream
@EnableHystrixDashboard
public class MonitoringApplication {

  public static void main(String[] args) {
    SpringApplication.run(MonitoringApplication.class, args);
  }

//  @Bean // HystrixDashboard监控需要的servlet，没有自动注册，需要手动注入
//  ServletRegistrationBean getServlet() {
//    HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(streamServlet);
//    servletRegistrationBean.setLoadOnStartup(1);
//    servletRegistrationBean.addUrlMappings("/hystrix.stream");
//    servletRegistrationBean.setName("HystrixMetricsStreamServlet");
//    return servletRegistrationBean;
//  }
}
