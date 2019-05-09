//package org.gy.framework.demoweb.config;
//
//
//import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.cloud.client.actuator.HasFeatures;
//import org.springframework.cloud.netflix.hystrix.dashboard.HystrixDashboardConfiguration;
//import org.springframework.cloud.netflix.hystrix.dashboard.HystrixDashboardController;
//import org.springframework.cloud.netflix.hystrix.dashboard.HystrixDashboardProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableConfigurationProperties(HystrixDashboardProperties.class)
//public class HystrixDashboardConfig {
//
//    @Autowired
//    private HystrixDashboardProperties dashboardProperties;
//
//    @Bean
//    public HasFeatures hystrixDashboardFeature() {
//        return HasFeatures.namedFeature("Hystrix Dashboard", HystrixDashboardConfiguration.class);
//    }
//
//    @Bean
//    public ServletRegistrationBean proxyStreamServlet() {
//        final HystrixDashboardConfiguration.ProxyStreamServlet proxyStreamServlet = new HystrixDashboardConfiguration.ProxyStreamServlet();
//        proxyStreamServlet.setEnableIgnoreConnectionCloseHeader(this.dashboardProperties.isEnableIgnoreConnectionCloseHeader());
//        final ServletRegistrationBean registration = new ServletRegistrationBean(proxyStreamServlet, "/proxy.stream");
//        registration.setInitParameters(this.dashboardProperties.getInitParameters());
//        return registration;
//    }
//
//    @Bean
//    public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() {
//        HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
//        ServletRegistrationBean<HystrixMetricsStreamServlet> servletRegistrationBean = new ServletRegistrationBean<HystrixMetricsStreamServlet>();
//        servletRegistrationBean.setServlet(hystrixMetricsStreamServlet);
//        servletRegistrationBean.setLoadOnStartup(10);
//        servletRegistrationBean.addUrlMappings("/hystrix.stream");
//        servletRegistrationBean.setName("HystrixMetricsStreamServlet");
//        return servletRegistrationBean;
//    }
//
//    @Bean
//    public HystrixDashboardController hsytrixDashboardController() {
//        return new HystrixDashboardController();
//    }
//}
