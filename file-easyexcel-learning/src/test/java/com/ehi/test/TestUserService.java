package com.ehi.test;

import com.ehi.service.UserExcelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;

/**
 * ClassName: TestUserService
 *
 * @Author: WangYiHai
 * @Date: 2020/5/9 10:59
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {

    @Autowired
    UserExcelService userExcelService;

    @Test
    public void testExport() throws FileNotFoundException {
        userExcelService.UserExcelImport();
    }

}