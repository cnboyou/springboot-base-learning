package com.ehi;

import com.ehi.server.HelloWorldService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@SpringBootApplication
public class HessianClientLearningApplication {

    @Bean
    public HessianProxyFactoryBean helloClient() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl("http://localhost:9108/HelloWorldService");
        factory.setServiceInterface(HelloWorldService.class);
        return factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(HessianClientLearningApplication.class, args);
    }

}
