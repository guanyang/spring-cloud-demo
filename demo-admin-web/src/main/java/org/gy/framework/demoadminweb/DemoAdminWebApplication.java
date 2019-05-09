package org.gy.framework.demoadminweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.gy.framework")
public class DemoAdminWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAdminWebApplication.class, args);
    }

}

