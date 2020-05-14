package com.ehi.md5;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ClassName: Md5Test
 *
 * @Author: WangYiHai
 * @Date: 2020/5/14 14:46
 * @Description: TODO
 */
public class Md5Test {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        InputStream in = null;
        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\test.txt");
        byte [] buf = new byte[1024];
        Integer len = 0;
        while((len = in.read(buf)) != -1) {
            md5Digest.update(buf, 0, len);
            fileOutputStream.write(buf, 0, len);
        }

        fileOutputStream.close();

        //计算MD5
        byte [] resultBytes = md5Digest.digest();
        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : resultBytes) {
            int a = b & 0xff;

            String hex = Integer.toHexString(a);

            if (hex.length() == 1) {
                hex = 0 + hex;
            }
            stringBuilder.append(hex);
        }
    }
}