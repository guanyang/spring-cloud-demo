package org.gy.framework.demogatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoGatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoGatewayServerApplication.class, args);
    }

}
