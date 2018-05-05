package com.piggymetrics.monitoring;


import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HystrixConfig {

  @Bean
  public HystrixMetricsStreamServlet hystrixMetricsStreamServlet() {
    return new HystrixMetricsStreamServlet();
  }

  @Bean
  public ServletRegistrationBean<HystrixMetricsStreamServlet> registration(HystrixMetricsStreamServlet servlet) {
    ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(servlet,"/actuator/hystrix.stream");
    registrationBean.setEnabled(true);
    return registrationBean;
  }

}