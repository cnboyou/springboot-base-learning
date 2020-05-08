package com.ehi.test;

import com.ehi.service.RunService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/8 18:39
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RunServiceTest {

    @Autowired
    RunService runService;

    @Test
    public void getTest() throws URISyntaxException {
        runService.getTestGet();
        runService.getTestPost();
        runService.getTestPostParam();
        runService.getTestPut();
        runService.getTestDel();
    }
}