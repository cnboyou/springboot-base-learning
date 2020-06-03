package com.ehi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogLearningApplication {
    private static final Logger logger = LoggerFactory.getLogger(LogLearningApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(LogLearningApplication.class, args);
        logger.error("log error");
        logger.info("log info");
        logger.warn("log warn");
        logger.debug("log debug");
    }


}
