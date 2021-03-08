package springcloud_eurekademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;




@SpringBootApplication
@EnableEurekaServer
public class SpringcloudEurekademoApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekademoApplication.class, args);
    }

}
