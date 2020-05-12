package com.ehi;

import feign.Feign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FeignClientLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignClientLearningApplication.class, args);

        Demo1Client client = Feign.builder().target(Demo1Client.class, "http://localhost:9001");
        String result = client.getDemo1("wang");
        System.out.println(result);

    }

}
