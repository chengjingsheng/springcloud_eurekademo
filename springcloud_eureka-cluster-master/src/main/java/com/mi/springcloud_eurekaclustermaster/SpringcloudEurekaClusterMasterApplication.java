package com.mi.springcloud_eurekaclustermaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudEurekaClusterMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaClusterMasterApplication.class, args);
    }

}
