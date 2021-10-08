package com.example.myfirstapigateway;

import contoller.MyTestController;
import filter.MyFilter;
import filter.MyPreRewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class MyFirstApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFirstApiGatewayApplication.class, args);
    }

    @Bean
    public MyFilter preFilter() {
        return new MyFilter();
    }

    @Bean
    public MyPreRewriteFilter preRewriteFilter(){
        return new MyPreRewriteFilter();
    }

    @Bean
    public MyTestController myController()
    {
        return new MyTestController();
    }

}
