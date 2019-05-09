package org.gy.framework.demoserviceweb;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.gy.framework.demoutil.hystrix.HystrixConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "org.gy.framework")
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class DemoServiceWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoServiceWebApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() {
        return HystrixConfig.getHystrixMetricsStreamServlet();
    }

}

