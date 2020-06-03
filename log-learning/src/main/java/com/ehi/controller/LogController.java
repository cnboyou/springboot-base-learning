package com.ehi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: LogController
 *
 * @Author: WangYiHai
 * @Date: 2020/5/29 10:22
 * @Description: TODO
 */
@RestController
public class LogController {
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/log")
    public void testLog() {
        logger.error("log error");
        logger.info("log info");
        logger.warn("log warn");
        logger.debug("log debug");
    }

}