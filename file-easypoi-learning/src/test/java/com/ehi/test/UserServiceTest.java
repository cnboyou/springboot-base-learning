package com.ehi.test;

import com.ehi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * ClassName: UserServiceTest
 *
 * @Author: WangYiHai
 * @Date: 2020/5/9 14:18
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testExport() {
    }

    @Test
    public void testImport() throws IOException {
        userService.importExcel();
    }

}