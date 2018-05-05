package com.piggymetrics.monitoring;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.cloud.stream.converter.CompositeMessageConverterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.converter.ConfigurableCompositeMessageConverter;

@Log4j2
@SpringBootApplication
@EnableTurbineStream
@EnableHystrixDashboard
public class MonitoringApplication {
  // for spring-boot-2.0,x using host:port/actuator/hystrix.stream
  public static void main(String[] args) {
    final SpringApplication application = new SpringApplication(MonitoringApplication.class);
    application.setBannerMode(Banner.Mode.OFF);
    application.setWebApplicationType(WebApplicationType.SERVLET);
    application.run(args);
  }

  @Bean
  public ConfigurableCompositeMessageConverter integrationArgumentResolverMessageConverter(
      CompositeMessageConverterFactory factory) {
    return new ConfigurableCompositeMessageConverter(
        factory.getMessageConverterForAllRegistered().getConverters());
  }

  /**
   * Registers HystrixMetricsStream Servlet that handles Hystrix Metrics
   *
   * @return ServletRegistrationBean
   */
  @Bean
  public ServletRegistrationBean servletRegistrationBean() {
    ServletRegistrationBean registration =
        new ServletRegistrationBean(new HystrixMetricsStreamServlet(), "/actuator/hystrix.stream");
    registration.setName("hystrixServlet");
    registration.setLoadOnStartup(1);
    return registration;
  }
}
