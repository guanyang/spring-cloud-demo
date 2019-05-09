//package org.gy.framework.demoadminweb.config;
//
//import org.gy.framework.demoutil.freemarker.HtmlTemplateLoader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//
//@Configuration
//public class FreeMarkerConfig {
//
//    @Autowired
//    private freemarker.template.Configuration configuration;
//
//    @PostConstruct
//    public void setSharedVariable() {
//        // 数据转义，防止xss攻击
//        configuration.setTemplateLoader(new HtmlTemplateLoader(configuration.getTemplateLoader()));
//    }
//}
