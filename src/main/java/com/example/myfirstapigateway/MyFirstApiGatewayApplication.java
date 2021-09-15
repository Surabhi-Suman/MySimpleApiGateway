package com.example.myfirstapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class MyFirstApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFirstApiGatewayApplication.class, args);
    }

}
