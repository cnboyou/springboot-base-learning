package com.ehi.test;

import com.ehi.RmiClientLearningApplication;
import com.ehi.service.IUserService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ClassName: RmiTest
 *
 * @Author: WangYiHai
 * @Date: 2020/5/8 11:26
 * @Description: TODO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RmiClientLearningApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RmiTest {
    private static final Logger logger = LoggerFactory.getLogger(RmiTest.class);
    Gson gson = new Gson();

    @Autowired
    private IUserService userService;

    @Test
    public void getUser(){
        logger.info("get user{}",gson.toJson(userService.getUserByName("admin")));
    }

}