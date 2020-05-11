package com.ehi.test;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: Test
 *
 * @Author: WangYiHai
 * @Date: 2020/5/11 18:38
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTests  {
    @Test
    public void jasyptTest() {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        // application.properties, jasypt.encryptor.password
        encryptor.setPassword("abc");
        // encrypt root
        System.out.println(encryptor.encrypt("root"));
        System.out.println(encryptor.encrypt("root"));
        System.out.println(encryptor.encrypt("root"));

        // decrypt, the result is root
        System.out.println(encryptor.decrypt("UP/yojB7ie3apnh3mLTU7w=="));
        System.out.println(encryptor.decrypt("ik9FE3GiYLiHwchiyHg9QQ=="));
        System.out.println(encryptor.decrypt("HR6xrpQ1tS4ghaLjiphoRw=="));
    }

}