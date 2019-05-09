//package org.gy.framework.demoweb.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//
//import java.util.Properties;
//
//@Configuration
//public class FreeMarkerConfig {
//
//    @Autowired
//    private FreeMarkerProperties freeMarkerProperties;
//
//    @Autowired
//    private freemarker.template.Configuration configuration;
//
//    @Bean
//    public FreeMarkerConfigurer freeMarkerConfigurer() {
//        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
//        configurer.setTemplateLoaderPaths(freeMarkerProperties.getTemplateLoaderPath());
//        configurer.setPreferFileSystemAccess(freeMarkerProperties.isPreferFileSystemAccess());
//        Properties settings = new Properties();
//        settings.putAll(this.freeMarkerProperties.getSettings());
//        configurer.setFreemarkerSettings(settings);
//        configurer.setDefaultEncoding("utf-8");
//        configurer.setConfiguration(configuration);
//        return configurer;
//    }
//
//}
