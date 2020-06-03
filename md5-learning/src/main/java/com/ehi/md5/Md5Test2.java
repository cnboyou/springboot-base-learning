package com.ehi.md5;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * ClassName: Md5Test2
 *
 * @Author: WangYiHai
 * @Date: 2020/6/3 11:21
 * @Description: TODO
 */
public class Md5Test2 {
    public static void main(String[] args){

        String md5Hex = DigestUtils.md5Hex("wyh1234" + "asfa");
        System.out.println(md5Hex);

    }
}